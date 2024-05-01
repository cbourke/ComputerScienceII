<?php

//Semester-to-semester specific elements

//python stuff
$python = "python3";

//valid on CSE
$jar   = "jar -J-Xmx2048m";
$java  = "java -Xmx2048m -Xlog:jni+resolve=off";
$javac = "javac -J-Xmx2048m";
$stagingDir   = "staging";

$junitJar = "junit-platform-console-standalone-1.9.2.jar";

$commonJars = "./lib/log4j-api-2.21.1.jar:./lib/log4j-core-2.21.1.jar:./lib/mysql-connector-j-8.2.0.jar";

$term = "Spring 2024";
$projectName = "YRLess Sales Management System";

$jarFile            = "Project.jar";
$dataConverterFile  = "./com/yrl/DataConverter.java";
$dataConverterClass = "com.yrl.DataConverter";

$csvNames  = array("Persons.csv", "Stores.csv", "Items.csv");
$xmlFiles  = array("Persons.xml", "Stores.xml", "Items.xml");
$jsonFiles = array("Persons.json", "Stores.json", "Items.json");

$masterTestCaseGlob = "../../common/testCases/case*";
$testCaseFiles = array("Persons.csv", "Stores.csv", "Items.csv", "Sales.csv", "SaleItems.csv", "output.txt");

$packageClass = "com.yrl.SalesReport";
$reportSource = "./com/yrl/SalesReport.java";

$sqlSchemaFile = "database.sql";
$sqlErDiagramFile = "database.png";
$sqlQueryFile = "queries.sql";
$sqlAccount = "c-cbourke3";
$sqlPasswd = "EeDie2Chuogh";

$mysqlJar = "mysql-connector-j-8.2.0.jar";

$javaClassPath = "./com/yrl/SalesReport.java";
$stagingFile  = "SalesDataStaging.java";
$stagingClass = "com.yrl.staging.SalesDataStaging";

function filesExist($fileList, $path = "./data/") {
  $result = true;
  foreach($fileList as $f) {
    $result = $result && file_exists($path . $f);
  }
  return $result;
}

?>
