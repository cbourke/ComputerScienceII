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
from canvas_utils import get_assignments
from canvas_utils import get_grade
from canvas_utils import set_grade

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


    lab_score = get_grade(module.lab.assignment_id, student.canvas_id)
    attend_score = get_grade(module.attendance.assignment_id, student.canvas_id)

    comment = f'Original attendance score: {attend_score}/5. '
    category = 1

    if attend_score < 5:
        if lab_score >= 20:
            attend_score = 5
            comment += f'Lab completed, attendance points awarded: {attend_score}/5'
            category = 2
        else:
            comment += f'Lab not complete, attendance points not awarded: {attend_score}/5'
            category = 3

    print("\t",comment)

    if commit_to_canvas:
        set_grade(module.attendance.assignment_id, student.canvas_id, attend_score, comment)
    return category

print(f"Processing Attendance for {module_name}...")
assignments = get_assignments(module_name)
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

results = (0, 0, 0)
for (nuid,s) in course.students.items():
    category = update_attendance(module, s)
    results[category] += 1
if not commit_to_canvas:
    print("Cowardly refused to commit changes to canvas, run with --commit jabroni.")
