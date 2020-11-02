"""
This module provides a thin wrapper interface to the Canvas
API utilizing CanvasAPIv1 (canvas-api-client).  It loads
the roster (including students, TAs, instructors) from the
Canvas course identified in the config.py module and creates
a roster list (a list of Person objects, see the person.py
module).

References:
 - UNL's Canvas API instance is at:
   https://canvas.unl.edu/api/v1/
 - UNL's Live instance:
   https://canvas.unl.edu/doc/api/live
 - CanvasAPIv1 (canvas-api-client) documentation:
   https://wgwz.github.io/canvas-lms-tools/canvas_api_client.html#module-canvas_api_client.v1_client

Note: the CanvasAPIv1 is somewhat limited.  There is no way way
to get the role of a user directly (student, TA, etc.).  In any
case, the course.py module is designed to manually define the 
role of graders and (non-grader) instructors so that we have finer
grained control on who is assigned to grade.
"""

from config import config
from person import Person
from canvas_api_client.v1_client import CanvasAPIv1
import sys
import http.client
import json

# create an instance of the canvas API
# using the configuration in config.py
api = CanvasAPIv1(config.canvasUrl, config.canvasApiKey)

### CUSTOM
url = "canvas.unl.edu"

def getMembers(groupId):
  path = ("/api/v1/groups/" + 
          str(groupId) + 
          "/memberships/?access_token="+config.canvasApiKey)      
  connection = http.client.HTTPSConnection(url)
  connection.request("GET", path)
  response = connection.getresponse()
  #print("Status: {} and reason: {}".format(response.status, response.reason))
  data = response.read().decode()
  connection.close();
  members = json.loads(data)
  result = []
  for member in members:
    userId = member['user_id']
    result.append(userId)
  return result
  
# Returns an array of 3-tuples of groups from canvas
# (groupId,"name",[membersCanvasIds])
def getGroups():
  path = ("/api/v1/courses/" + 
          config.canvasCourseId + 
          "/groups/?per_page=100&access_token="+config.canvasApiKey)      
  connection = http.client.HTTPSConnection(url)
  connection.request("GET", path)
  response = connection.getresponse()
  #print("Status: {} and reason: {}".format(response.status, response.reason))
  data = response.read().decode()
  connection.close();

  result = []
  groups = json.loads(data)
  for group in groups:
    numMembers = group['members_count']
    if numMembers > 1:
      groupId = group['id']
      name = group['name']
      members = getMembers(groupId)
      result.append( (groupId,name,members) )
  return result

pages = api.get_course_users(config.canvasCourseId)
# the API lazy loads elements and uses pagination, 
# so a double iteration is necessary.
roster = []
for page in pages:
    for u in page:
        try: 
            canvasLogin = None
            canvasEmail = None
            if 'login_id' in u:
                canvasLogin = u['login_id']
            else:
                print("ERROR: no login_id for user")
                print(u)
            if 'email' in u:
                canvasEmail = u['email']
            else:
                print("ERROR: no email for user")
                print(u)
            p = Person(
              nuid        = u['sis_user_id'],
              canvasId    = u['id'],
              name        = u['sortable_name'], #format: "last, first"
              canvasLogin = canvasLogin,
              canvasEmail = canvasEmail
            )
            roster.append(p)
        except:
            print("Problem with record:")
            print(u)
            e = sys.exc_info()[0]
            print( "<p>Error: %s</p>" % e )

groups = getGroups()
