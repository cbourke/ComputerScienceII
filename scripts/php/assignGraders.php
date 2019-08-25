<?php

/**
 * This is a script to "assign" graders to (groups) of students
 * for a particular assignment.  The result is simply just a print
 * out that you can use to direct your GTAs/LAs as to who should
 * grade whom.  
 *
 * The script uses the Canvas API and CSE's User Database (UDB) which
 * needs to be configured in the loadStudents.php script (see that
 * script for more documentation).  
 *
 * Run this script as:
 *   php assignGraders.php
 * from the CSE server after you have configured.  Hack as necessary.
 */

include_once("loadStudents.php");

$groups = loadGroups();

/**
 * CSE Logins of the graders for your course
 * 
 */
$graders = array(
  "jstudent", 
  "kstudent");

shuffle($groups);
//shuffle graders so the first ones are not always stuck with the +1 assignments
shuffle($graders);

$submissionsPerGrader = floor( count($groups) / count($graders) );
$assignment = array();
$i = 0;
foreach($graders as $grader) {
  $assigned = array();
  for($j=0; $j<$submissionsPerGrader; $j++) {
    $assigned[] = $groups[$i];
    $i++;
  }
  $assignment[$grader] = $assigned;
}
//evenly assign left over students
foreach($graders as $grader) {
  if($i<count($groups)) {
    array_push($assignment[$grader], $groups[$i]);
    $i++;
  }
}

$min = floor( count($groups) / count($graders) );
$max = ceil( count($groups) / count($graders) );    
$result = "Assigned Grading:\n";
$result .= sprintf("Number of graders:  %3d\n", count($graders));
$result .= sprintf("Number of groups/students: %3d\n", count($groups));
$result .= sprintf("Each grader will grade %d - %d submissions\n", $min, $max);

foreach($assignment as $grader => $groupSet) {
  $result .= sprintf("%s\n", $grader);
  foreach($groupSet as $group) {
    if(count($group) === 0) {
      //ignore
    } else if(count($group) === 1) {
      $result .= sprintf("\t%-30s (%s, %s)\n", $group[0]->getName(), $group[0]->getCseLogin(), $group[0]->getCourse()->getName());
    } else if(count($group) === 2) {
      $result .= sprintf("\t%-30s (%s) teamed with %s (%s) [%s, %s]\n", 
        $group[0]->getName(), $group[0]->getCseLogin(), 
        $group[1]->getName(), $group[1]->getCseLogin(), 
        $group[0]->getCourse()->getName(),
        $group[0]->getGroupName());
    } else {
      printf("ERROR: group %s has %d members\n", "foo", count($group));
    }
  }
}
print $result;
//print_r($assignment);

?>
