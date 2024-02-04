"""
Grader script for JUnit-based labs.
"""
import argparse

"""
JUnit and test wrapper class
"""
test_wrapper_file = "TestWrapper.java"
test_wrapper_class = "unl.soc.TestWrapper"
junit_jar = "junit-platform-console-standalone-1.9.2.jar"

"""
Mapping of lab (numbers using the handin directory names) to
the fully qualified path/class names of the JUnit tests for each.
"""
junit_tests = {
    1: "unl.soc.StatisticsTests",
    2: "unl.soc.NaturalTests unl.soc.ChildCreditTests",
    3: "unl.soc.BaseballTests unl.soc.DnaAnalysisTests",
    #TODO: the rest of the labs...
    #"L04": "unl.cse.library.BatchTest",
    #"L05": "com.cinco.payroll.BatchTest",
    #"L09": "unl.cse.albums.BatchTest",
    #"L10": "unl.cse.albums.BatchTest",
    #"L11": "unl.cse.trucks.BatchTest",
    #"L13": "unl.cse.sorting.BatchTest"
}

parser = argparse.ArgumentParser()
parser.add_argument("lab_number", help="The lab number to be graded (ex: 1 would be Lab 1.0 in in ~/handin/L01)", type=int)
parser.add_argument("--login", help="specify to grade a single canvas login", default=None)
parser.add_argument("--commit", action='store_true', help=
  """Push the lab scores to canvas. The default is to not
  modify the canvas gradebook so a test run can be made.
  """, default=False)
args = parser.parse_args()
push_to_canvas = args.commit
if push_to_canvas:
    print(f"Committing all results to canvas")
else:
    print(f"Cowardly not pushing results to canvas, run with --commit to do so.")

import glob
import os
import pprint
import subprocess
from config import config
from course import course
from canvasUtils import getAssignments
from canvasUtils import setGrade
from canvasUtils import getGrade

lab_name = f"Lab {args.lab_number:.1f}"
lab_number = f"L{args.lab_number:02d}"
if args.lab_number not in junit_tests:
    print(f"No such lab number, {args.lab_number}")
    sys.exit(1)
junit_test_classes = junit_tests[args.lab_number]
login = args.login

# If login is specified, we cut the roster to a single student
if login:
    students = {k:v for (k,v) in course.students.items() if v.canvasLogin == login}
else:
    students = course.students

canvas_assignment = getAssignments(lab_name)
if len(canvas_assignment) != 1:
    print(f"Unable to get (unique) canvas assignment:")
    pprint.pprint(canvas_assignment)
    sys.exit(1)
else:
    canvas_assignment = canvas_assignment[0]

base_path = config.handin_directory
points = 20
staging_dir = "lab_staging"
java = "/usr/bin/java"
javac = "/usr/bin/javac"

print(f"Grading {lab_name} using {junit_test_classes} in {lab_number}...")
print(f"  Canvas Assignment: {canvas_assignment}")

(good,bad,ugly) = (0,0,0)

for nuid,p in students.items():
  print(f"  {p.name} ({p.nuid})...")
  login = p.canvasLogin
  canvas_id = p.canvas_id
  full_path = f"{base_path}{lab_number}/{login}/"
  score = getGrade(canvas_assignment.id, p.canvas_id)
  if score is not None:
    print(f"    SKIPPED: Score already in gradebook: {score}/20")
  elif not os.path.exists(full_path):
    print(f"    FAILED: no directory ({full_path}), 0/20")
    comment = f"No Submission 0/20"
    if push_to_canvas:
        setGrade(canvas_assignment.id, p.canvas_id, 0, comment)
    print(f"\tRESULT: {comment}")
    ugly += 1
  else:
    os.chdir(base_path + lab_number + "/" + login)
    os.system("rm -rf "+staging_dir)
    os.mkdir(staging_dir)
    #cp library
    os.system(f"cp ../../common/{test_wrapper_file} ./{staging_dir}")
    os.system(f"cp ../../common/{junit_jar} ./{staging_dir}")
    os.system("cp -R ../common/* ./"+staging_dir)
    os.system("cp *.java " + staging_dir)
    os.chdir(staging_dir)

    jars = ":".join(glob.glob("./lib/*.jar"))
    javac_cmd = f"{javac} -cp ./:./{junit_jar}:{jars} -d . *.java"

    os.system(javac_cmd)

    java_cmd = f"{java} -cp ./:./{junit_jar}:{jars} {test_wrapper_class} {junit_test_classes}"

    result = subprocess.run([java_cmd, ""], shell=True, stdout=subprocess.PIPE)
    output = result.stdout.decode('utf-8')
    num_fail = result.returncode
    if not result or num_fail > 0:
        comment = f"Did not compile or run or one or more JUnit tests failed: 0/20"
        score = 0
        bad += 1
    else:
        comment = f"All JUnit tests passed: {points}/{points}"
        score = points
        good += 1
    if push_to_canvas:
        setGrade(canvas_assignment.id, p.canvas_id, score, comment)
    print(f"\tRESULT: {comment}, {score}")
    os.chdir("..")
    os.system("rm -rf "+staging_dir)

print(f"Passed: {good}")
print(f"Failed: {bad} ")
print(f"Nosubm: {ugly}")
