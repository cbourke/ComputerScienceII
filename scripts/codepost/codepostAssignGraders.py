"""
TODO: update this entire script - out of synch with CS1

This script interfaces with canvas, the SoC's handin file server
(cse-linux-01.unl.edu) and codepost.io to assign graders to student
submissions for a particular assignment and pushes all submission
files to codepost.io.

Usage: python3 codepostAssignGraders.py cse_handin_assignment_name codepost_assignment_id

where
  - cse_handin_assignment_name is the "name" of the handin assignment
    (corresponds to the handin directory created)
  - codepost_assignment_id is the codepost assignment (database) ID.
    You can retrieve this by first running codepostListCourseInfo.py

In detail:

 1. It loads the current roster from Canvas (and separates
    instructors/graders/students using the config.py params)
 2. It attempts to map NUIDs to CSE logins to grab submissions
    (failures will be excluded or "orphaned")
 3. It randomizes grading assignments (evenly distributing
    them among graders) and outputs an assignment report to
    the standard output
 4. It scans the handin directory for files whose extensions
    match those in the config.py file and pushes them to
    codepost.io associated them with the assigned grader.

This script assumes that the course and assignments have
already been setup and that no files have been submitted
to codepost already.  Preexisting submissions will result
in a fatal error (so it is best to do this cleanly and/or
wipe the assignment on codepost.io and restart)
"""
import argparse
import sys
import os
import codepost
import pprint
from config import config
from course import course
from fileUtils import getFiles
from codepostUtils import get_assignment_id

parser = argparse.ArgumentParser()
parser.add_argument("handin_assignment_number", help=
  """The handin directory in which files are stored;
  ex: A1 would be expected to be in {handin_directory}/A1)
  """
  )
parser.add_argument("codepost_assignment_name", help=
  """The codepost.io assignment name; ex: "Assignment 1.0"
  """)
parser.add_argument("--commit", action='store_true', help=
  """Commmits the changes to the gradebook.  If this is not
  provided, the script will run in coward mode and not make
  any modifications, only reporting what would have been done.
  """)
args = parser.parse_args()

codepost.configure_api_key(config.codepost_api_key)

handin_assignment_number = args.handin_assignment_number
codepost_assignment_name = args.codepost_assignment_name
codepost_assignment_id = get_assignment_id(codepost_assignment_name)
commit = args.commit

assignment_dir = f"{config.handin_directory}{handin_assignment_number}/"
if not os.path.exists(assignment_dir):
    print(f"assignment directory: {assignment_dir} does not seem to exist")
    print("perhaps you need more practice operating your computer machine")
    exit(1)

grading_assignment = course.getGradingAssignment()
s = course.assignmentToString(grading_assignment)
print(s)

csv = course.assignmentToCSV(grading_assignment)
f = open(handin_assignment_number+".csv", "w")
f.write(csv)
f.close()

def pushAssignments(grading_assignment):
  for grader,groups in grading_assignment.items():
    for g in groups:
      s = g.members[0]
      path = f"{assignment_dir}{s.canvasLogin}/"
      print(f"Pushing files in {path}...")
      try:
        files = getFiles(path)
      except:
        e = sys.exc_info()[0]
        print("Error: %s" % e )
        files = {}
      if files:
        if commit:
          submission = codepost.submission.create(
            assignment=codepost_assignment_id,
            students=[m.canvasEmail for m in g.members],
            grader=grader.canvasEmail)
        for (fullPath,name,ext),contents in files.items():
          print(f"  pushing {name}...")
          if commit:
            codepost.file.create(
              name=name,
              code=contents,
              extension=ext,
              submission=submission.id
            )

if not commit:
  print("Cowardly refusing to push source files to codepost.io; rerun with --commit if you wanna.")

pushAssignments(grading_assignment)
