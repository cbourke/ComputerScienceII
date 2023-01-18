"""
This module provides a collection of functions that give a
thin wrapper interface to the Canvas API.

References:
 - UNL's Canvas API instance is at:
   https://canvas.unl.edu/api/v1/
 - UNL's Live instance:
   https://canvas.unl.edu/doc/api/live

"""

from config import config

from assignment import Assignment
from person import Person
from group import Group

import http.client
import json
import re
import requests

canvasUrl = "https://canvas.unl.edu"
baseApiUrl = f"{canvasUrl}/api/v1/courses"
defaultParams = {
  "access_token": config.canvasApiKey,
  "per_page": 100
}

def parse_header_links(value):
    """Return a dict of parsed link headers proxies.
    This function is stolen from the requests package with
    moderate modifications so that it returns an actual
    dicionary mapping <code>rel</code> values to <code>url</code>s
    """

    links = []

    replace_chars = " '\""

    for val in re.split(", *<", value):
        try:
            url, params = val.split(";", 1)
        except ValueError:
            url, params = val, ''

        link = {}

        link["url"] = url.strip("<> '\"")

        for param in params.split(";"):
            try:
                key, value = param.split("=")
            except ValueError:
                break

            link[key.strip(replace_chars)] = value.strip(replace_chars)

        links.append(link)
    result = {}
    for foo in links:
        result[foo['rel']] = foo['url']

    return result

def getGroupCategoryId(groupSetName):
  """
  Retrieves the Canvas "Group Set" ID (internally this is
  designated as a Group Category) given the name of the
  group such as "Assignment Pairs".  If no match is found,
  None is returned.
  """
  url = f"{baseApiUrl}/{config.canvasCourseId}/group_categories"
  response = requests.get(url, params=defaultParams)
  categories = response.json()
  for category in categories:
    if category['name'] == groupSetName:
      return category['id']
  return None

def getMembers(groupId):
  """
  Retrieves the canvas user IDs of the members of the given
  canvas group.
  """
  url = f"{canvasUrl}/api/v1/groups/{groupId}"
  response = requests.get(url, params=defaultParams)
  members = response.json()
  result = []
  for member in members:
    userId = member['user_id']
    result.append(userId)
  return result

def getGroupTuples(groupSetName=None):
  """
  Retrieves groups and members from Canvas based on the
  given Group Set (group category).  If none is provided,
  every group is retrieved.  Only groups with more than
  one member are included though.

  Returns an array of 3-tuples consisting of:
    (groupId,"groupName",[membersCanvasIds])

  """
  groupCategoryId = None
  if groupSetName is not None:
    groupCategoryId = getGroupCategoryId(groupSetName)

  url = f"{baseApiUrl}/{config.canvasCourseId}/groups"
  response = requests.get(url, params=defaultParams)
  groups = response.json()

  result = []
  for group in groups:
    if group['members_count'] > 1 and (groupCategoryId is None or group['group_category_id'] == groupCategoryId):
      groupId = group['id']
      name = group['name']
      members = getMembers(groupId)
      result.append( (groupId,name,members) )
  return result

def getRoster():
  """
  Retrieves the complete roster of the course from Canvas
  including all students, instructors, TAs, etc.

  TODO: reconsider using https://canvas.unl.edu/api/v1/courses/{course_id}/enrollments

  which has a role field to determine what they are; this would not
  relieve us of the need to keep track of NUIDs in config.py as we
  would still need to determine who is a grader and who is not

  Returns a mapping of {NUID => Person objects}
  """
  #dev notes: the canvas API limits pagination (per_page)
  #  to at most 100, so we're forced to deal with it manually
  roster = {}

  moreData = True
  users = []
  path = ("/api/v1/courses/" +
          config.canvasCourseId +
          "/users/?page=1&per_page=100&access_token=" +
          config.canvasApiKey)
  connection = http.client.HTTPSConnection("canvas.unl.edu")
  while moreData:
    connection.request("GET", path)
    response = connection.getresponse()
    data = response.read().decode()
    moreUsers = json.loads(data)
    users += moreUsers

    headers = response.info()
    linkHeader = headers['Link']
    links = parse_header_links(linkHeader)
    if 'next' in links:
      #python 3.9+: path = links['next'].removeprefix("https://canvas.unl.edu") + "&access_token=" + config.canvasApiKey
      path = links['next'][22:] + "&access_token=" + config.canvasApiKey
    else:
      moreData = False

  connection.close()

  for user in users:
    canvasId = user['id']
    if 'login_id' in user:
        canvasLogin = user['login_id']
    else:
        canvasLogin = None
    nuid = user['sis_user_id']
    name = user['sortable_name'] #format: "last, first"
    if 'email' in user:
        email = user['email']
    else:
        email = None
    p = Person(
      nuid,
      name,
      canvasId,
      canvasLogin,
      email
    )
    roster[p.nuid] = p
  return roster

def getGroups(roster):
  """
  Returns a list of Group objects from Canvas based on
  the Group Set name in the configuration file.  If no such
  group set exists or if an individual is not part of a group,
  they are placed in a group of one.

  In addition, each Person object in the given roster is
  associated with its group.
  """
  groups = []
  if config.gradingGroupName is not None:
    groupData = getGroupTuples(config.gradingGroupName)
    for gd in groupData:
      #(groupId,"groupName",[membersCanvasIds])
      g = Group(gd[0],gd[1])
      members = []
      for memberCanvasId in gd[2]:
        for nuid,p in roster.items():
          if p.canvasId == memberCanvasId:
            members.append(p)
            p.group = g
            break
      g.addMembers(members)
      groups.append(g)

  # iterate through roster and create groups of one:
  for nuid,p in roster.items():
    # but only if they have not already been assigned to a group
    if p.group is None:
      g = Group()
      g.addMembers([p])
      p.group = g
      groups.append(g)
  return groups

def getSections():
  """
  Retrieves all sections from the canvas course.  You can futher use

  https://canvas.unl.edu/api/v1/sections/{sectionId}/enrollments

  to get enrollments only for that section (which will include
  teachers, TAs, etc. but will have a `role` field to determine what
  it is.
  """
  url = f"{baseApiUrl}/{config.canvasCourseId}/sections"
  response = requests.get(url, params=defaultParams)
  resultData = response.json()
  return None

def getGrade(assignmentId, userId):
  """
  Returns the grade (generally a float) from the canvas gradebook
  for the given assignment and user.  If no grade has been assigned,
  the result will be None.
  """
  url = f"{baseApiUrl}/{config.canvasCourseId}/assignments/{assignmentId}/submissions/{userId}"
  response = requests.get(url, params=defaultParams)
  resultData = response.json()
  return resultData['score']


def setGrade(assignmentId, userId, score = None, comment = None):
   """
   Sets/updates the grade in the canvas gradebook for the given assignment
   for the given user.  If the score/commenet are None, they are not
   updated.  If the comment is specified, canvas *adds* the comment and
   does not modify or delete old ones.
   """
   url = f"{baseApiUrl}/{config.canvasCourseId}/assignments/{assignmentId}/submissions/{userId}"

   data = {}
   if score is not None:
       data['comment'] = { "text_comment": comment }
   if comment:
       data['submission'] = { "posted_grade": score }
   response = requests.put(url, params=defaultParams, json=data)
   return

def getAssignmentGroupId(assignmentGroupName):
     """
     Retrieves the canvas assignment group ID for the assignment group with the
     given name.  Returns None if no such assignment group exists.

     """
     groups = getAssignmentGroups()
     groups = {i for i in groups if groups[i] == assignmentGroupName}
     return next(iter(groups), None)

def getAssignmentGroups():
   """
   Retrieves all assignment groups from canvas.  Returns a map of IDs
   to group names:

     {id -> name}

   """
   url = f"{baseApiUrl}/{config.canvasCourseId}/assignment_groups"
   response = requests.get(url, params=defaultParams)
   resultData = response.json()
   aGroups = {}
   for r in resultData:
       id, name = r['id'], r['name']
       aGroups[id] = name
   return aGroups

def getAssignmentsInGroup(groupId):
   """
   Retrieves all assignments from an assignment group (for the given groupId)
   from canvas.  Returns a list of assignments.

   """
   url = f"{baseApiUrl}/{config.canvasCourseId}/assignment_groups/{groupId}/assignments"
   response = requests.get(url, params=defaultParams)
   resultData = response.json()
   assignments = []
   for r in resultData:
       id = r['id']
       a = Assignment(r['id'], r['name'], r['points_possible'])
       assignments.append(a)
   return assignments
