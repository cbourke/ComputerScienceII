<?php

/**
 * This is a hackish script that connects to canvas's API
 * and then to CSE's User Database (to map NUIDs to CSE 
 * logins) and then groups them according to their groups
 * in canvas.  
 * 
 * The groups should be setup in canvas by bulk creating
 * a group and then allowing the students to join a group.
 * Students who do not join are still included by graded as
 * an individual.
 * 
 * You'll need to create an access token and enter it below
 * and you'll need UDB database access.  Due to firewallin'
 * you'll need to run this script on the CSE server.
 */

/**
 * This is your canvas access token that you must generate
 * in canvas to connect to the API.
 */
$accessToken = "";


/**
 * CSE's UDB (User Database) username/password
 * This database is necessary to map NUIDs -> CSE Logins
 */
$udbUsername = "";
$udbPassword = "";



/**
 * This is a list of course(s) of data you want to pull.
 * We allow multiple ones in case you want to consolidate
 * multiple sections across gradesr.
 * A course consists of:
 *   - A name; ex: "CSCE156"
 *   - A canvas course ID; ex: "53628"
 * You can get these through the API or you can simply look
 * at the URL in canvas; ex:
 *   https://canvas.unl.edu/courses/53650 
 *   (53650 is the course ID)
 *   
 */
$courses = array(
  new Course("CSCE156", "53628"),
  new Course("CSCE156H", "53647")
);

class Course {
  
  private $name = null;
  private $canvasCourseId = null;
  
  public function __construct($name, $canvasCourseId) {
    $this->name = $name;
    $this->canvasCourseId = $canvasCourseId;
  }
  
  public function getName() {
    return $this->name;
  }

  public function getCanvasCourseId() {
    return $this->canvasCourseId;
  }
  
}

class Student {
  
  private $canvasId = null;
  private $nuid = null;
  private $name = null;
  private $cseLogin = "none";
  private $canvasLogin = null;
  private $course = null;
  private $groupName = "foo";

  public function __construct($canvasId, $nuid = "", $name, $canvasLogin, $course = null) {
    $this->canvasId = $canvasId;
    $this->nuid = $nuid;
    $this->name = $name;
    $this->canvasLogin = $canvasLogin;
    $this->course = $course;
  }
  public function setCseLogin($cseLogin) {
    $this->cseLogin = $cseLogin;
  }
  public function getCseLogin() {
    return $this->cseLogin;
  }
  public function getCanvasId() {
    return $this->canvasId;
  }
  public function getCanvasLogin() {
    return $this->canvasLogin;
  }
  public function getNuid() {
    return $this->nuid;
  }
  public function getName() {
    return $this->name;
  }
  public function getCourse() {
    return $this->course;
  }
  public function setGroupName($groupName) {
    return $this->groupName = $groupName;
  }
  public function getGroupName() {
    return $this->groupName;
  }
  public function __toString() {
    return sprintf("%-40s %s %10s %10s %s", $this->name, $this->nuid, $this->canvasId, $this->canvasLogin, $this->cseLogin);
  }
  public static function compareStudent($a, $b) {
    return strcmp($a->getName(), $b->getName());
  }
}

/**
 * Returns a map of NUID => student objects
 */
function getStudents($courses) {
  global $accessToken;
  $students = array();
  foreach($courses as $course) {
    $result = json_decode(file_get_contents("https://canvas.unl.edu//api/v1/courses/".$course->getCanvasCourseId()."/students/?access_token=".$accessToken));
    foreach($result as $s) {
      //$canvasId, $nuid, $name, $canvasLogin
      //Test Student will not have an NUID
      if($s->sis_user_id != null) {
        $students[$s->sis_user_id] = new Student($s->id, $s->sis_user_id, $s->sortable_name, $s->login_id, $course);
      }
    }
  }
  return $students;
}

/**
 * Returns a map of NUID => cseLogins
 */
function getCseLogins($nuids) {
  $in = $ids = join("','",$nuids);
  $results = array();
  //soooo icky
  $sql = "select login, nuid, student_type from view_login_nuid where nuid in ('$in');";

  global $udbUsername;
  global $udbPassword;
  $servername = "cse-apps.unl.edu";
  $username = $udbUsername;
  $password = $udbPassword;
  $dbname = "udb";

  // Create connection
  $conn = new mysqli($servername, $username, $password, $dbname);
  // Check connection
  if ($conn->connect_error) {
      die("Connection failed: " . $conn->connect_error);
  } 

  $result = $conn->query($sql);

  if ($result->num_rows > 0) {
      // output data of each row
      while($row = $result->fetch_assoc()) {
        $nuid = $row["nuid"];
        $results[$nuid] = $row["login"];
      }
  }
  $conn->close();
  return $results;
}

function loadStudents($courses) {
  
  $roster = getStudents($courses);
  $nuids = array_keys($roster);
  $cseLogins = getCseLogins($nuids);
  foreach($cseLogins as $nuid => $cseLogin) {
    $roster[$nuid]->setCseLogin($cseLogin);
  }
  return $roster;  
}

function groupStudents($roster, $courses) {
  global $accessToken;

  $groups = array();
  $groupIds = array();
  
  foreach($courses as $course) {  
    $result = json_decode(file_get_contents("https://canvas.unl.edu/api/v1/courses/".$course->getCanvasCourseId()."/groups" . '/?per_page=100000&access_token='.$accessToken));
    foreach($result as $group) {
      if($group->members_count > 0) {
        $groupIds[$group->id] = $group->name;
      }
    }
  }

  //print_r($groupIds);
  foreach($groupIds as $groupId => $groupName) {
    $result= json_decode(file_get_contents("https://canvas.unl.edu/api/v1/groups/".$groupId."/memberships/?access_token=".$accessToken));
    $group = array();
    //TODO: consider a remap to canvasId => student instead of NUID for efficiency
    foreach($result as $member) {
      $canvasId = $member->user_id;
      $student = $roster[$canvasId];
      //add to the group:
      $group[] = $student;
      //set student's group name:
      $student->setGroupName($groupName);
      //remove from roster
      unset($roster[$canvasId]);
    }
    usort($group, "Student::compareStudent");
    $groups[] = $group;
    //print_r($result);
  }
  foreach($roster as $student) {
    $groups[] = array($student);
  }
  return $groups;
}

function loadGroups() {
  global $courses;
  $roster = loadStudents($courses);
  $rosterByCanvasId = array();
  foreach($roster as $nuid => $student) {
    $rosterByCanvasId[$student->getCanvasId()] = $student;
  }
  //print_r($roster);
  $groups = groupStudents($rosterByCanvasId, $courses);
  return $groups;
}




?>