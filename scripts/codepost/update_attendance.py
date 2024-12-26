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

import argparse
from types import SimpleNamespace
import sys

from course import course
from canvasUtils import getAssignments
from canvasUtils import getGrade
from canvasUtils import setGrade

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

module_name = args.module_name
commit_to_canvas = args.commit

def update_attendance(module, student):
    """
    Updates the "differential attendance" score in the canvas gradebook for the
    given module and student.

    * If the attendance score for attendance is already set no action is taken
    * If the lab score is 20, attendance points are awarded
    * If the lab score is less than 20, no attenacne points are awarded
    """
    print(f'Processing student: {student}...')


    lab_score = getGrade(module.lab.id, student.canvas_id)
    attend_score = getGrade(module.attendance.id, student.canvas_id)

    comment = f'Original attendance score: {attend_score}/5.'

    if attend_score < 5:
        if lab_score >= 20:
            attend_score = 5
            comment += f'Lab completed, attendance points awarded: {attend_score}/5'
        else:
            comment += f'Lab not complete, attendance points not awarded: {attend_score}/5'

    print("\t",comment)

    if commit_to_canvas:
        setGrade(module.attendance.id, student.canvas_id, attend_score, comment)

print(f"Processing Attendance for {module_name}...")
assignments = getAssignments(module_name)
module = SimpleNamespace()
module.lab        = next(filter(lambda x: 'Lab' in x.name and x.points == 20, assignments), None)
module.attendance = next(filter(lambda x: 'Lab' in x.name and x.points == 5, assignments), None)
print("Assignments:")
print(f"  Lab:     {module.lab}")
print(f"  Attend:  {module.attendance}")

a = input("Proceed? Y/N")

if a != "Y":
    print("Cowardly exiting...")
    sys.exit(1)

for (nuid,s) in course.students.items():
    update_attendance(module, s)
if not commit_to_canvas:
    print("Cowardly refused to commit changes to canvas, run with --commit jabroni.")
