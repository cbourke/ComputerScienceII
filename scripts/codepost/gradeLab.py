import argparse
import subprocess
import os

""" 
Mapping of non-standard lab packages.  In general the 
default batch tester package/class will be unl.cse.BatchTest
"""
labToPackage = {
    "L04": "unl.cse.library.BatchTest",
    "L05": "com.cinco.payroll.BatchTest"
}

parser = argparse.ArgumentParser()
parser.add_argument("labNumber", help="The directory name of the lab to be graded (ex: L01 would be in ~/handin/L01)")
parser.add_argument("--login", help="specify to grade a single login", default=None)
parser.add_argument("--path", help="specify a fully qualified Java package/class to run (ex: unl.cse.BatchTest)")
args = parser.parse_args()

from config import config
from course import course

labNumber = args.labNumber
login = args.login
batchTestClass = args.path

if not args.path:
    if labNumber in labToPackage:
        batchTestClass = labToPackage[labNumber];
    else:
        batchTestClass = "unl.cse.BatchTest"

if login:
  #TODO: find student by cseLogin
  students = {k:v for (k,v) in course.students.items() if v.cseLogin == login}
else:
  students = course.students

#import pprint
#pprint.pprint(students)

# CSCE 156 configurations
basePath = "/home/grad/Classes/cse156/handin/"
stagingDir = "lab_staging"
java = "/usr/bin/java"
javac = "/usr/bin/javac"

csvResult = "cseLogin,canvasId,points,pass,fail,totes\n"

#print("Grading " + str((labNumber,login,batchTestClass)) + "...")

print("Grading...");

for nuid,p in students.items():
  login = p.cseLogin
  canvasId = p.canvasId
  fullPath = basePath + labNumber + "/" + login + "/"
  if not os.path.exists(fullPath):
    csvResult += "%s,%s,%d,%d,%d,%d\n"%(login,canvasId,0,-1,-1,-1)
  else:
    os.chdir(basePath + labNumber + "/" + login)
    os.system("rm -rf "+stagingDir)
    os.mkdir(stagingDir)
    #cp library
    os.system("cp -R ../common/* ./"+stagingDir)
    os.system("cp *.java " + stagingDir)
    os.chdir(stagingDir)
    os.system(javac + " -cp ./lib/*.jar -d . *.java")
    args = " -cp .:lib/* " + batchTestClass + " "
    result = subprocess.run([java+args, ""], shell=True, stdout=subprocess.PIPE).stdout.decode('utf-8')
    if not result:
      result = "0,-2,-2,-2" #submitted but failed
    csvResult += "%s,%s,%s\n"% (login,canvasId,result)
    os.chdir("..")
    os.system("rm -rf "+stagingDir)
    
print(csvResult)
