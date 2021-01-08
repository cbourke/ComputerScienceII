from canvas import roster
from canvas import groups
from config import config
from group import Group
import udb
import copy
import math
import random

class Course:
    #all persons will be by NUID
    instructorNuids = []
    #map: "NUID" => Person
    instructors = {}

    graderNuids = []
    graders = {}
    
    students = {}
    
    #students with no cse login for some reason
    orphans = {}
    
    def __init__(self, instructorNuids = [], graderNuids = []):
        self.instructorNuids = instructorNuids
        self.graderNuids = graderNuids
        # 1a. load full roster from canvas
        self.roster = roster
        # 2. get as many cse logins as possible
        # 2a. dump NUIDs to map
        nuids = [p.nuid for p in self.roster]
        nuidsToCseLogins = dict(zip(nuids, [None for x in nuids]))
        # 2b. update
        nuidsToCseLogins = udb.mapNuidsToCseLogins(nuidsToCseLogins,config.nuidToCseLoginPickle)
        # 2c. update roster instances
        for p in self.roster:
            if p.nuid in nuidsToCseLogins and nuidsToCseLogins[p.nuid] is not None:
                p.cseLogin = nuidsToCseLogins[p.nuid]
                
        #3. filter into the appropriate group
        #   This is done manually as the "role" is not available from canvas 
        #   using the canvas API and we want more fine-grained control anyway
        #   - If there is no cse login, they are ignored
        #   - Otherwise, they are either a student XOR instructor/grader
        #     - instructors can be graders
        for p in self.roster:
            if p.cseLogin is None:
                self.orphans[p.nuid] = p
            else:
                if p.nuid in instructorNuids or p.nuid in graderNuids:
                    if p.nuid in instructorNuids: 
                        self.instructors[p.nuid] = p
                    if p.nuid in graderNuids: 
                        self.graders[p.nuid] = p
                else:
                    self.students[p.nuid] = p
        #4. Update the groups so that every student is in a group
        #   For those in a canvas group of 2 or more or in their
        #   own "group of one"
        #   self.groups data model:
        #   [(groupId, 'Group Name', [canvasIds])]
        canvasGroups = groups
        self.groups = []
        for group in canvasGroups:
            #canvasGroupId, canvasGroupname
            g = Group(group[0], group[1])
            members = []
            for memberCanvasId in group[2]:
                for p in self.roster:
                    if p.canvasId == memberCanvasId:
                        members.append(p)
                        p.group = g;
                        break
            g.addMembers(members)
            self.groups.append(g)
        # iterate through students and create "group of one"
        for nuid,s in self.students.items():
            if s.group is None:
                g = Group()
                g.addMembers([s])
                s.group = g 
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
        r += "Orphans (%d): \n"%(len(self.orphans))
        for nuid,p in self.orphans.items():
            r += str(p) + "\n"
        r += "Groups: \n"
        for g in self.groups:
            r += str(g)
        r += "CSV Data:\n"
        r += "nuid,name,canvasId,canvasEmail,canvasLogin,cseLogin\n"
        for nuid,p in self.students.items():
            r += p.toCsv()
        return r

    # returns a mapping of graders (Person objects) to
    #  a list of groups (Group objects) they are assigned to
    def getAssignment(self):
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
            grader = self.graders[graderNuids[i]]
            assignment[grader].append(group)
            i = (i+1)%n
        return assignment
        
    def assignmentToString(self,assignment):
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
            for g in groups:
                r += "%s\t%s\n"%(grader.name,g.members[0].canvasEmail)
        return r

course = Course(instructorNuids=config.instructorNuids, 
           graderNuids=config.graderNuids)

def printCourse():
    print(course)

if __name__ == "__main__":
    printCourse()


