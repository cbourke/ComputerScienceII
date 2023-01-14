"""
This module provides a thin wrapper interface to the Canvas
API.  It loads the roster (including students, TAs, instructors)
from the Canvas course identified in the config.py module and creates
a roster list (a list of Person objects, see the person.py module).

References:
 - UNL's Canvas API instance is at:
   https://canvas.unl.edu/api/v1/
 - UNL's Live instance:
   https://canvas.unl.edu/doc/api/live

"""

from config import config
from person import Person
from group import Group
import sys
import http.client
import json
import re

canvasHost = "canvas.unl.edu"

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
  path = ("/api/v1/courses/" +
          config.canvasCourseId +
          "/group_categories/?access_token=" +
          config.canvasApiKey)
  connection = http.client.HTTPSConnection(canvasHost)
  connection.request("GET", path)
  response = connection.getresponse()
  data = response.read().decode()
  connection.close()
  categories = json.loads(data)
  for category in categories:
    if category['name'] == groupSetName:
      return category['id']
  return None

def getMembers(groupId):
  """
  Retrieves the canvas user IDs of the members of the given
  canvas group.
  """
  path = ("/api/v1/groups/" +
          str(groupId) +
          "/memberships/?access_token="+config.canvasApiKey)
  connection = http.client.HTTPSConnection(canvasHost)
  connection.request("GET", path)
  response = connection.getresponse()
  data = response.read().decode()
  connection.close()
  members = json.loads(data)
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
  path = ("/api/v1/courses/" +
          config.canvasCourseId +
          "/groups/?per_page=100&access_token="+config.canvasApiKey)
  connection = http.client.HTTPSConnection(canvasHost)
  connection.request("GET", path)
  response = connection.getresponse()
  data = response.read().decode()
  connection.close()

  result = []
  groups = json.loads(data)
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
  connection = http.client.HTTPSConnection(canvasHost)
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

# {NUID => Person}
roster = getRoster()
# [Group]
groups = getGroups(roster)

if __name__ == "__main__":
    for nuid,p in roster.items():
        print(p)
