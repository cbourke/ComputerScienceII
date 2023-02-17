import argparse
import subprocess
import os
import glob
import pprint

"""
Mapping of non-standard lab packages.  In general the
default batch tester package/class will be unl.cse.BatchTest
"""
labToPackage = {
    "L04": "unl.cse.library.BatchTest",
    "L05": "com.cinco.payroll.BatchTest",
    "L09": "unl.cse.albums.BatchTest",
    "L10": "unl.cse.albums.BatchTest",
    "L11": "unl.cse.trucks.BatchTest",
    "L13": "unl.cse.sorting.BatchTest"
}

parser = argparse.ArgumentParser()
parser.add_argument("labNumber", help="The lab number to be graded (ex: 1 would be Lab 1.0 in in ~/handin/L01)", type=int)
parser.add_argument("--login", help="specify to grade a single login", default=None)
parser.add_argument("--push", action='store_true', help=
  """Push the lab scores to canvas. The default is to not
  modify the canvas gradebook so a test run can be made.
  """, default=False)
args = parser.parse_args()
pushToCanvas = args.push
if pushToCanvas:
    print(f"Pushing all results to canvas")
else:
    print(f"Cowardly not pushing results to canvas, run with --push to do so.")

from config import config
from course import course
from assignment import Assignment
from canvasUtils import getAssignments
from canvasUtils import setGrade
from canvasUtils import getGrade

labName = f"Lab {args.labNumber:.1f}"
labNumber = f"L{args.labNumber:02d}"
login = args.login
batchTestClass = "unl.cse.BatchTest"

if labNumber in labToPackage:
    batchTestClass = labToPackage[labNumber];

# If login is specified, we cut the roster to a single student
if login:
  students = {k:v for (k,v) in course.students.items() if v.cseLogin == login}
else:
  students = course.students

canvasAssignment = getAssignments(labName)
if len(canvasAssignment) != 1:
    print(f"Unable to get (unique) canvas assignment:")
    pprint.pprint(canvasAssignment)
else:
    canvasAssignment = canvasAssignment[0]

basePath = config.handinDirectory
points = 20
stagingDir = "lab_staging"
java = "/usr/bin/java"
javac = "/usr/bin/javac"

csvResult = "cseLogin,canvasId,points,pass,fail,totes\n"

print(f"Grading {labName} using {batchTestClass} in {labNumber}...")
print(f"  Canvas Assignment: {canvasAssignment}")

(good,bad,ugly) = (0,0,0)

for nuid,p in students.items():
  print(f"  {p.name} ({p.nuid})...")
  login = p.cseLogin
  canvasId = p.canvasId
  fullPath = f"{basePath}{labNumber}/{login}/"
  score = getGrade(canvasAssignment.id, p.canvasId)
  if score is not None:
    print(f"    SKIPPED: Score already in gradebook: {score}/20")
  elif not os.path.exists(fullPath):
    print(f"    FAILED: no directory ({fullPath}), 0/20")
    comment = f"No Submission 0/20"
    if pushToCanvas:
        setGrade(canvasAssignment.id, p.canvasId, 0, comment)
    print(f"\tRESULT: {comment}")
    csvResult += "%s,%s,%d,%d,%d,%d\n"%(login,canvasId,0,-1,-1,-1)
    ugly += 1
  else:
    os.chdir(basePath + labNumber + "/" + login)
    os.system("rm -rf "+stagingDir)
    os.mkdir(stagingDir)
    #cp library
    os.system("cp -R ../common/* ./"+stagingDir)
    os.system("cp *.java " + stagingDir)
    os.chdir(stagingDir)

    jars = ":".join(glob.glob("./lib/*.jar"))
    javacCmd = javac + " -cp ./:"+jars+" -d . *.java"

    os.system(javacCmd)

    javaCmd = java + " -cp ./:"+jars+" "+batchTestClass

    result = subprocess.run([javaCmd, ""], shell=True, stdout=subprocess.PIPE).stdout.decode('utf-8')
    if not result:
      comment = f"One or more JUnit tests failed: 0/20"
      score = 0
      result = "0,-2,-2,-2" #submitted but failed
      bad += 1
    else:
      # verify: points,pass,fail,totes: 0,5,1,6
      tokens = result.split(",")
      score = int(tokens[0])
      passed = int(tokens[1])
      failed = int(tokens[2])
      if score > 0:
          comment = f"All JUnit tests passed: {score}/20"
          good += 1
      else:
          comment = f"JUnit results: {passed} passed, {failed} failed; score: {score}/20"
          bad += 1
    if pushToCanvas:
        setGrade(canvasAssignment.id, p.canvasId, score, comment)
    print(f"\tRESULT: {comment}")
    csvResult += "%s,%s,%s\n"% (login,canvasId,result)
    os.chdir("..")
    os.system("rm -rf "+stagingDir)

print(csvResult)

print(f"Passed: {good}")
print(f"Failed: {bad} ")
print(f"Nosubm: {ugly}")
