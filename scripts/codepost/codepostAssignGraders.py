import sys
import os
from os import walk
import codepost
from config import config
from course import course
from fileUtils import getFiles 

# This script interfaces with codepost and brings everything
# together:
# 1. It pulls the current roster from Canvas (and separates
#    instructors/graders/students using the config.py params)
#    and separates students into groups as assigned in canvas
#    (students with no group form a "group of one")
# 2. It attempts to map NUIDs to CSE logins to grab submissions
#    (failures will be excluded or "orphaned")
# 3. It then randomizes grading assignments (tries to evenly
#    distribute among the graders) and produces a hardcopy report
# 4. It then scans the handin directory for specific files and
#    submits them to codepost.io with the assigning the grader to
#    each.
#
# This script assumes that the course and assignments have 
# already been setup and that no submissions have been made.
# Preexisting submissions will result in a fatal error (so it
# is best to do this cleanly and/or wipe the assignment on
# codepost.io and restart)

if(len(sys.argv) != 3):
  print("usage: cse_handin_assignment_name codepost_assignment_id")  
  exit(1)

assignmentDir = config.handinPath+sys.argv[1]+"/"
assignmentId = int(sys.argv[2])

codepost.configure_api_key(config.codePostApiKey)

gradingAssignment = course.getAssignment()
s = course.assignmentToString(gradingAssignment)
print(s)

csv = course.assignmentToCSV(gradingAssignment)
f = open(sys.argv[1]+".csv", "w")
f.write(csv)
f.close()

def pushAssignments(gradingAssignment):
    for grader,groups in gradingAssignment.items():
        for g in groups:
            s = g.members[0]
            path = assignmentDir+s.cseLogin+"/"
            print("looking in " + path + "...")            
            try:
              files = getFiles(path)
            except: 
              e = sys.exc_info()[0]
              print("Error: %s" % e )
              files = {}
            if files:
              submission = codepost.submission.create(
                 assignment=assignmentId,
                 students=[m.canvasEmail for m in g.members],
                 grader=grader.canvasEmail)
              for (fullPath,name,ext),contents in files.items():
                  print("pushing " + name)
                  codepost.file.create(
                     name=name,
                     code=contents,
                     extension=extension,
                     submission=submission.id
                  )

pushAssignments(gradingAssignment)
