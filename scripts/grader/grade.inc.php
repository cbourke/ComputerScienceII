<?php

/**
 * This is a collection of classes that allows you to create test suites of
 * test modules having test cases.  Each class supports various functionality
 * including pre and post test commands (command line output is captured and
 * output by the script).
 */
abstract class Tester {

  const version = "2.2.8";

  private static $collapseIdCounter = 100;
  public static $borderStyle = "none;"; //1px solid red;";

  public static $PRINT_EXIT_CODE = false;
  public static $exitCodes = array(0   => "No Error",
  	 		     	   127 => "'Something wrong with the machine?' (Yeah, that's the best POSIX documentation I could find--awesome, right?)",
				   134 => "Your job received an abort signal, weird, huh?",
				   137 => "CPU Time Limit Exceeded",
				   139 => "Segmentation Fault");

  /**
   * Returns the contents of the given file in an HTML formatted
   * manner.
   */
  public static function fileGetContents($fileName) {
    $result = "<p>File: " . basename($fileName) . "</p>";
    if(file_exists($fileName)) {
      $result .= "<pre>" . htmlentities(file_get_contents($fileName)) . "</pre>";
    } else {
      $result .= "<p><span style='color: red; font-weight: bold;'>ERROR: </span>File, $fileName does not exist.  Cannot display contents.</p>";
    }
    return $result;
  }

  /**
   * Returns a map of file names => file contents from all files
   * with the specified $extension contained in the given zip file
   */
  public static function getFileContentsFromZipArchive($zipFileName, $extension) {

    $fileContents = array();
    if(!file_exists($zipFileName)) {
      return $fileContents;
    }

    $tmpDir = "./temp_zipDir";
    mkdir($tmpDir);
    copy($zipFileName, "$tmpDir/$zipFileName");
    chdir($tmpDir);
    exec("unzip $zipFileName");


    $files = new RecursiveIteratorIterator(new RecursiveDirectoryIterator("./"), RecursiveIteratorIterator::SELF_FIRST);
    foreach($files as $name => $object){
      if(substr_compare($name, $extension, -strlen($extension), strlen($extension)) === 0) {
        $fname = $object->getFilename();
        $fileContents[$fname] = file_get_contents($name);
      }
    }

    chdir("..");
    exec("rm -R $tmpDir");
    return $fileContents;
  }

  /**
   * Returns a map of file names => file contents from all files
   * with the specified $extension contained in the current directory
   * as well as all subdirectories
   */
  public static function getAllFileContents($extension = ".java") {

    $fileContents = array();
    $files = new RecursiveIteratorIterator(new RecursiveDirectoryIterator("./"), RecursiveIteratorIterator::SELF_FIRST);
    foreach($files as $name => $object){
      if(substr_compare($name, $extension, -strlen($extension), strlen($extension)) === 0) {
        $fname = $object->getFilename();
        $fileContents[$fname] = file_get_contents($name);
      }
    }
    return $fileContents;
  }

  /**
   * Formats the given $content inside a collapsible <div> element
   */
  public static function getCollapsibleDiv($title, $content) {

    //increment the static counter for unique IDs
    Tester::$collapseIdCounter++;
    $jsCommand = "\$(\"#collapseId" . Tester::$collapseIdCounter . "\").toggle(\"blind\"); $(this).text() == \"[-]\"?$(this).text(\"[+]\"):$(this).text(\"[-]\");";
    $htmlDiv = "<div style='clear: both'><h2><span style='cursor: pointer;' onclick='$jsCommand'>[-]</span> $title</h2></div>\n" .
               "<div id='collapseId" . Tester::$collapseIdCounter . "' style='margin-left: 2em;'>" . $content  . "</div>";
    return $htmlDiv;
  }

  protected $label;
  protected $expectedOutput;
  protected $isGrader;
  private $message;
  private $sourceFiles;
  private $preTestCommands;
  private $postTestCommands;
  private $requiredFiles = array();

  public function __construct($label = null, $expectedOutput = null, $isGrader = false) {
    $this->label = $label;
    $this->expectedOutput = $expectedOutput;
    $this->isGrader = $isGrader;
    $this->sourceFiles = Array();
    $this->preTestCommands = Array();
    $this->postTestCommands = Array();
  }

  abstract protected function executeCommands();

  protected function executeCommand($cmd, &$output, &$exitCode) {
    $lastLineResult = exec($cmd . " 2>&1 ", $output, $exitCode);
    $fullOutput = "";
    foreach($output as $line) {
      $fullOutput .= $line . "\n";
    }
    if($exitCode > 0 && Tester::$PRINT_EXIT_CODE) {
      $exitCodeMsg = isset(Tester::$exitCodes[$exitCode]) ? Tester::$exitCodes[$exitCode] : "Unknown";
      $fullOutput .= "WARNING: process exited with a(n) $exitCodeMsg ($exitCode) error code\n";
    }
    $output = $fullOutput;
  }

  public function addSourceFile($fileName, $fileContents = null) {
    if($fileContents == null) {
      //file content not provided, so let's get it for them
      // but only if it exists
      if(file_exists($fileName)) {
        $fileContents = file_get_contents($fileName);
      } else {
        $fileContents = "ERROR: no such file";
      }
    }
    $this->sourceFiles[$fileName] = $fileContents;
    return $this;
  }

  public function addMessage($message) {
    $this->message = $message;
  }

  public function addPreTestCommand($cmd, $label = null) {
    if(!empty($label)) {
      $this->preTestCommands[$label] = $cmd;
    } else {
      $this->preTestCommands[] = $cmd;
    }
    return $this;
  }

  public function addPostTestCommand($cmd) {
    $this->postTestCommands[] = $cmd;
    return $this;
  }

  public function addRequiredFile($file) {
    $this->requiredFiles[] = $file;
    return $this;
  }

  public function run() {

    Tester::$collapseIdCounter++;
    $result = "";

    if(!empty($this->label)) {
      $cmd = "\$(\"#collapseId" . Tester::$collapseIdCounter . "\").toggle(\"blind\"); $(this).text() == \"[-]\"?$(this).text(\"[+]\"):$(this).text(\"[-]\");";
      $result .= "<div style='clear: both;'><h2><span style='cursor: pointer;' onclick='$cmd'>[-]</span> $this</h2></div>\n";
    }

    //wrap in a collapsible div here, start...
    $result .= "<div id='collapseId" . Tester::$collapseIdCounter . "' style='margin-left: 1em;'>";

    //add message here
    if(!empty($this->message)) {
      $result .= "<p>" . $this->message . "</p>";
    }


    if(count($this->requiredFiles) > 0) {
      $result .= "<p>Checking for required files...</p>\n";
      $fileMissing = false;
      foreach($this->requiredFiles as $file) {
        if(!file_exists($file)) {
          $result .= "<p><span style='color: red'>ERROR:</span> Required file $file not handed in, cannot be graded.\n</p>";
          $fileMissing = true;
        }
      }
      if($fileMissing) {
        $result .= "<p><span style='color: red'>ERROR:</span> One or more files missing, skipping the rest of the grading process, hand these in first!</p>";
        //end collapse div here since we short circuit the rest of the grading process...
        $result .= "</div>";
	      return $result;
      }
    }

    //only print source code if its a grader
    if($this->isGrader) {
      $sourceFileDivs = "";
      foreach($this->sourceFiles as $file => $contents) {
        $divContent = '<pre class="prettyprint linenums"><code">' . htmlentities($contents) . '</code></pre>';
        $collapsibleDiv = self::getCollapsibleDiv($file, $divContent);
	      $sourceFileDivs .= $collapsibleDiv;
      }
      if(!empty($sourceFileDivs)) {
        $result .= self::getCollapsibleDiv("Source Files", $sourceFileDivs);
      }
    }

    if(count($this->preTestCommands) > 0) {
      $fullOutput = "";
      foreach($this->preTestCommands as $label => $cmd) {
        $output = "";
	      $exitCode = "";
	      $this->executeCommand($cmd, $output, $exitCode);

	      if(!empty($output)) {
          $output = "<pre>" . htmlentities($output) . "</pre>";
        }

        if(is_string($label)) {
          $fullOutput .= self::getCollapsibleDiv($label, $output);
	      } else {
          $fullOutput .= $output;
	      }
      }
      if(!empty($fullOutput)) {
        $result .= self::getCollapsibleDiv("Pre Testing Commands", $fullOutput);
      }
    }

    if(!empty($this->expectedOutput)) {
        $result .= "<hr/>";
        $result .= "<div style='clear: both;'>";
        $result .= "<div style='float: left'>";
        $result .= "<h4>Expected Output</h4>";
	      $result .= "<div style='padding: 10px; border: ".self::$borderStyle."'>";
        $result .= "<pre>".htmlentities($this->expectedOutput)."</pre>\n";
 	      $result .= "</div>"; //end of pre-div
        $result .= "</div>"; //end of float: left
	      $result .= "</div>"; //end of clear: both
	      $result .= $this->executeCommands();
    } else {
      $result .= $this->executeCommands();
    }

    if(count($this->postTestCommands) > 0) {
      $fullOutput = "";
      foreach($this->postTestCommands as $cmd) {
        $output = "";
        $exitCode = "";
        $this->executeCommand($cmd, $output, $exitCode);

        if(!empty($output)) {
          $fullOutput = "<pre>" . htmlentities($output) . "</pre>";
        }
      }
      if(!empty($fullOutput)) {
        $result .= self::getCollapsibleDiv("Post Testing Commands", $fullOutput);
      }
    }

    //end collapse div started above
    $result .= "</div>";

    return $result;
  }

  public function __toString() {
    return "Test Case $this->label";
  }

}

class TestModule extends Tester {

  private $testCases;
  public function __construct($label = null, $expectedOutput = null, $isGrader = false, $isFormatted = false) {
    parent::__construct($label, $expectedOutput, $isGrader, $isFormatted);
    $this->testCases = Array();
  }

  public function addTestCase($testCase) {
    $this->testCases[] = $testCase;
  }

  protected function executeCommands() {
    $result = "";
    if(count($this->testCases) > 0) {
      $result .= "Running Test Cases...\n";
      foreach($this->testCases as $testCase) {
        $result .= $testCase->run();
      }
    }
    return $result;
  }

  public function __toString() {
    return "Test Module $this->label";
  }

}

class TestSuite extends Tester {
  private $testModules;

  public function __construct($label = null, $expectedOutput = null, $isGrader = false, $isFormatted = false) {
    parent::__construct($label, $expectedOutput, $isGrader, $isFormatted);
    $this->testModules = Array();
  }

  public function addTestModule($testModule) {
    $this->testModules[] = $testModule;
  }

  protected function executeCommands() {
    $result = "";
    if(count($this->testModules) > 0) {
      $result .= "Running Test Module Commands...\n";
      foreach($this->testModules as $testModule) {
        $result .= $testModule->run();
      }
    }
    return $result;
  }

  public function run() {
    $result = "";
    $result .= parent::run();
    return $result;
  }

  public function __toString() {
    return "Test Suite $this->label";
  }
}

class TestCase extends Tester {

  private $testCaseCommands;

  public function __construct($label = null, $expectedOutput = null, $isGrader = false, $isFormatted = false) {
    parent::__construct($label, $expectedOutput, $isGrader, $isFormatted);
    $this->testCaseCommands = Array();
  }

  public function addTestCaseCommand($cmd) {
    $this->testCaseCommands[] = $cmd;
    return $this;
  }

  protected function executeCommands() {
    $result = "";
    if(count($this->testCaseCommands) > 0) {

      $fullOutput = "";
      foreach($this->testCaseCommands as $cmd) {
        $output = "";
        $exitCode = "";
        $this->executeCommand($cmd, $output, $exitCode);
	      $fullOutput .= $output;
      }

      //if there was no expected output, float it left:
      $float = empty($this->expectedOutput) ? "left" : "right";
      $result .= "<div style='float: $float;'>\n";
      $result .= "<h4>Program Output</h4>\n";
      $result .= "<div style='padding: 10px; border: ".self::$borderStyle."'><pre>".htmlentities($fullOutput)."</pre></div>\n";
      $result .= "</div>";
    }
    return $result;
  }

  public function __toString() {
    return "Test Case $this->label";
  }

}

?>
