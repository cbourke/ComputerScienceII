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
args = parser.parse_args()

from config import config
from course import course
from assignment import Assignment
from canvasUtils import getAssignments
from canvasUtils import setGrade

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

for nuid,p in students.items():
  print(f"  {p.name} ({p.nuid})...")
  login = p.cseLogin
  canvasId = p.canvasId
  fullPath = f"{basePath}{labNumber}/{login}/"
  if not os.path.exists(fullPath):
    print(f"    FAILED: no directory ({fullPath}), 0/20")
    comment = f"No Submission 0/20"
    setGrade(canvasAssignment.id, p.canvasId, 0, comment)
    csvResult += "%s,%s,%d,%d,%d,%d\n"%(login,canvasId,0,-1,-1,-1)
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
    else:
      comment = f"All JUnit tests passed: 20/20"
      score = 20
    setGrade(canvasAssignment.id, p.canvasId, score, comment)
    csvResult += "%s,%s,%s\n"% (login,canvasId,result)
    os.chdir("..")
    os.system("rm -rf "+stagingDir)

print(csvResult)
