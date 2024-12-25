"""
This script is intended for use in CSCE 156 to facilitate its differential
lab attendance policy.  Students are required to attend and if they do
they earn attendance points (which are manually entered into the canvas
gradebook).  However, high-performing students that do not attend can still
earn the points provided they meet a certain threshold of points for the
lab.

This script interfaces with the UNL Canvas instance to update the attendance
points according to this policy.
"""

from config import config
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("module_name", help=
  """The (partial) module name in canvas to update attendance scores.
  Examples: " 1.0" or "12.0" or "Strings"
  """)
parser.add_argument("--commit", action='store_true', help=
  """Commmits the changes to the gradebook.  If this is not
  provided, the script will run in coward mode and not make
  any modifications, only reporting what would have been done.
  """)
args = parser.parse_args()

from course import course
from assignment import Assignment
from canvasUtils import getAssignments
from canvasUtils import getGrade
from canvasUtils import setGrade
import requests
import pprint
from types import SimpleNamespace

module_name = args.moduleName
commitToCanvas = args.commit

def get_module(module_name):
  """
  Returns a "module" representation of a CS2 module in canvas (lab and
  attendance) for the module with the given `module_name`
  """

  assignments = getAssignments(module_name)

  result = SimpleNamespace()
  result.lab = next(filter(lambda x: 'Lab' in x.name and 'Attendance' not in x.name, assignments), None)
  result.attendance = next(filter(lambda x: 'Module' in x.name and 'Reading' not in x.name, assignments), None)

  return result

def updateAttendance(module, student):
    """
    Updates the "differential attendance" score in the canvas gradebook for the
    given module and student.

    * If the attendance score for both a lab and hack are already awarded,
      no action is taken
    * If the score(s) necessary to determine if attendance should be awarded,
      a warning is printed and no action is taken
    * If the scores on the reading/lab/hack meet the threshold (>=75%), the
      attendance scores are updated along with a comment
    * Otherwise, the scores are not modified, but a comment is added
    """
    print(f"Processing student: {student}...")
    readingScore = getGrade(module.reading.id, student.canvasId)
    labScore = getGrade(module.lab.id, student.canvasId)
    hackScore = getGrade(module.hack.id, student.canvasId)
    attendScore = getGrade(module.attendance.id, student.canvasId)

    r = [readingScore, labScore, hackScore, attendScore]
    if None in r:
        print(f"\tNot updating attendance due to missing score")
        return

    comment = f"Original attendance score: {attendScore}/10.  "

    if attendScore < 10:
        subTotal = readingScore + labScore + hackScore
        percent = subTotal / 55.0
        if percent >= .75:
            comment += f"75% threshold met ({subTotal:.2f} / 55.0 = {percent*100:.2f}%).  Attenance points awarded."
            attendScore = 10
        else:
            comment += f"75% threshold NOT met ({subTotal:.2f} / 55.0 = {percent*100:.2f}%)."

    print("\t",comment)

    if commitToCanvas:
        setGrade(module.attendance.id, student.canvasId, attendScore, comment)

module = getModule(moduleName)
print(f"Processing Attendance for {moduleName}...")
print("Assignments:")
print(f"  Reading: {module.reading}")
print(f"  Lab:     {module.lab}")
print(f"  Hack:    {module.hack}")
print(f"  Attend:  {module.attendance}")

a = input("Proceed? Y/N")

if a != "Y":
    print("Cowardly exiting...")
    exit(1)

for (nuid,s) in course.students.items():
    updateAttendance(module, s)
if not commitToCanvas:
    print("Cowardly refused to commit changes to canvas, run with --commit jabroni.")
