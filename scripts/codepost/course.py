"""
A class and module to support an entire course.  A
course is connected to both a Canvas course.

This module loads data from the Canvas course using
its API (see canvas.py).

Using the configuration module (see config.py), users
are separated according to instructors (non-graders),
graders, and students.

The Course class itself maintains these groups both
as lists of NUIDs (strings) and maps of NUID => Person (object).

When used as an executable file, all course data is
loaded and printed to the standard output.  Otherwise,
a global object (course) is made available.

In addition to the course data, two functions provide
functionality to randomly create a grading assignment
(see getAssignment() and assignmentToString()).
"""

from config import config
from canvas import roster
from canvas import groups
from group import Group
import copy
import math
import random

class Course:
    #all persons will be by NUID
    instructorNuids = []
    instructors = {}

    graderNuids = []
    graders = {}

    students = {}
    groups = []

    def __init__(self, instructorNuids = [], graderNuids = []):
        self.instructorNuids = instructorNuids
        self.graderNuids = graderNuids
        # 1. load full roster from canvas
        # {NUID => Person}
        self.roster = roster

        #3. filter into the appropriate group
        #   This is done manually as the "role" is not available from canvas
        #   using the canvas API and we want more fine-grained control anyway
        #   - Otherwise, they are either a student XOR instructor/grader
        #     - instructors can be graders
        for nuid,p in self.roster.items():
            if nuid in instructorNuids or nuid in graderNuids:
                if nuid in instructorNuids:
                    self.instructors[nuid] = p
                if nuid in graderNuids:
                    self.graders[nuid] = p
            else:
                self.students[nuid] = p
        # update groups to exclude instructors and graders [Group]
        self.groups = []
        # for each group in canvas.groups:
        for g in groups:
          # if the group leader (first listed) is a student, then add them
          if len(g.members) == 0:
            print(f"Warning: Group {g.canvasGroupName} ({g.canvasGroupId}) has no members")
          elif g.members[0].nuid in self.students:
            self.groups.append(g)

    def __str__(self):
        r = "Instructors (%d): \n"%(len(self.instructors))
        for nuid,p in self.instructors.items():
            r += str(p) + "\n"
        r += "Graders (%d): \n"%(len(self.graders))
        for nuid,p in self.graders.items():
            r += str(p) + "\n"
        r += "Students (%d): \n"%(len(self.students))
        for nuid,p in self.students.items():
            r += str(p) + "\n"
        r += "Groups: \n"
        for g in self.groups:
            r += str(g)
        r += "CSV Data:\n"
        r += "nuid,name,canvas_id,canvas_email,canvas_login\n"
        for nuid,p in self.students.items():
            r += p.toCsv()
        r += "Emails:\n"
        for nuid,p in self.students.items():
            r += str(p.canvas_email) + "\n"
        return r

    def getGradingAssignment(self):
        """
        Returns a randomized mapping of graders (Person objects)
        to a list of students (Group objects) they are assigned
        to grade.

        Assignments are made in a round-robin manner so that the
        same grader(s) are not always assigned more (or fewer) to
        grade.
        """
        graderNuids = list(self.graders.keys())
        groups = copy.deepcopy(self.groups)
        random.shuffle(graderNuids)
        random.shuffle(groups)
        assignment = {}
        #initialize lists
        for gNuid in graderNuids:
            assignment[self.graders[gNuid]] = []
        i = 0
        n = len(graderNuids)
        for group in groups:
            g = self.graders[graderNuids[i]]
            assignment[g].append(group)
            i = (i+1)%n
        return assignment

    def assignmentToString(self,assignment):
        """
        Given an assignment (a mapping of Person objects to a
        list of Person objects) as generated by getAssignment(),
        this function will create a human-readable string of the
        grading assignments.  For convenience, the printed string
        is in order of name for both graders and students
        """
        r  = "Assigned Grading\n"
        r += "================\n"
        min = math.floor(len(self.groups) / len(self.graders))
        max = math.ceil(len(self.groups) / len(self.graders))
        r += "Each grader will grade %d - %d groups\n"%(min,max)
        #dump graders to list of Person objects
        graders = list(assignment.keys())
        graders.sort(key=lambda x: x.name)
        for grader in graders:
          groups = assignment[grader]
          groups.sort(key=lambda x: x.members[0].name)
          n = len(groups)
          r += "%s (%d assigned)\n"%(grader.name,n)
          for g in groups:
            r += str(g)
        return r

    def assignmentToCSV(self,assignment):
        r = ""
        graders = list(assignment.keys())
        graders.sort(key=lambda x: x.name)
        for grader in graders:
            groups = assignment[grader]
            groups.sort(key=lambda x: x.members[0].name)
            nameTokens = grader.name.split(",")
            graderLast = nameTokens[0].strip()
            graderFirst = nameTokens[1].strip()
            for g in groups:
                nameTokens = g.members[0].name.split(",")
                studentLast = nameTokens[0].strip()
                studentFirst = nameTokens[1].strip()
                r += "%s,%s,%s,%s,%s,%s,%s\n"%(grader.nuid,graderLast,graderFirst,g.members[0].nuid,studentLast,studentFirst,g.members[0].canvas_email)
        return r

course = Course(instructorNuids=config.instructor_nuids,
           graderNuids=config.grader_nuids)
"""
The course object for this module initialized with with
the course data defined in config.py
"""

def printCourse():
    print(course)

if __name__ == "__main__":
    printCourse()
