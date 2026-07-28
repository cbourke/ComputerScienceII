"""
This module provides a collection of functions that give a
thin wrapper interface to the Canvas API.

References:
 - UNL's Canvas API instance is at:
   https://canvas.unl.edu/api/v1/
 - UNL's Live instance:
   https://canvas.unl.edu/doc/api/live

"""

import http.client
import json
import re
from urllib.parse import urlencode
import requests

from config import config

from assignment import Assignment
from person import Person
from group import Group

CANVAS_HOST = "canvas.unl.edu"
CANVAS_BASE_API_PATH = "/api/v1"
DEFAULT_PARAMS = {
  "access_token": config.canvas_api_key,
  "per_page": 100
 }

def get_canvas_data(path, user_parameters={}):
    """A general canvas API interface that will retrieve data (using
    HTTP GET) from the provided `path` (ex: `"courses/{course_id}/assignments").
    This function uses pagination to ensure all data is retrieved.

    The raw response data is automatically deserialized (assumed to
    be JSON) and returned; generally this is a list of JSON objects.

    Parameters can be provided in the optional `user_parameters` field,
    any user-provided parameters will supercede the defaults.
    """

    more_data = True
    data = []
    params = urlencode({**DEFAULT_PARAMS, **user_parameters})
    path = f"{CANVAS_BASE_API_PATH}/{path}?{params}"
    connection = http.client.HTTPSConnection(CANVAS_HOST)
    while more_data:
        connection.request("GET", path)
        response = connection.getresponse()
        raw_data = response.read().decode()
        new_data = json.loads(raw_data)
        if isinstance(new_data, dict):
            # API has returned a single json object instead of a list of objects
            # immediately close and return
            connection.close()
            return new_data
        data += new_data
        headers = response.info()
        link_header = headers["Link"]
        links = parse_header_links(link_header)
        if "next" in links:
            # python 3.9+: path = links['next'].removeprefix("https://canvas.unl.edu") + "&access_token=" + config.canvasApiKey
            path = links["next"][22:] + f"&{params}"
        else:
            more_data = False
    connection.close()
    return data


def parse_header_links(value):
    """Return a dict of parsed link headers proxies.
    This function is stolen from the requests package with
    moderate modifications so that it returns an actual
    dicionary mapping `rel` values to `url`s
    """

    if value is None:
        return {}

    links = []

    replace_chars = " '\""

    for val in re.split(", *<", value):
        try:
            url, params = val.split(";", 1)
        except ValueError:
            url, params = val, ""

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
    for link in links:
        result[link["rel"]] = link["url"]
    return result


def get_group_category_id(group_set_name):
    """
    Retrieves the Canvas "Group Set" ID (internally this is
    designated as a Group Category) given the name of the
    group such as "Assignment Pairs".  If no match is found,
    None is returned.
    """
    path = f"/courses/{config.canvas_course_id}/group_categories"
    result_data = get_canvas_data(path)
    for category in result_data:
        if category["name"] == group_set_name:
            return category["id"]
    return None


def get_members(group_id):
    """
    Retrieves the canvas user IDs of the members of the given
    canvas group.
    """
    path = f"/groups/{group_id}/memberships"
    result_data = get_canvas_data(path)
    result = []
    for member in result_data:
        user_id = member["user_id"]
        result.append(user_id)
    return result


def get_group_tuples(group_set_name=None):
    """
    Retrieves groups and members from Canvas based on the
    given Group Set (group category).  If none is provided,
    every group is retrieved.  Only groups with more than
    one member are included though.

    Returns an array of 3-tuples consisting of:
      `(group_id,"groupName",[membersCanvasIds])`

    """
    group_category_id = None
    if group_set_name is not None:
        group_category_id = get_group_category_id(group_set_name)
    path = f"/courses/{config.canvas_course_id}/groups"
    result_data = get_canvas_data(path)
    result = []
    for group in result_data:
        if group["members_count"] > 1 and (
            group_category_id is None or group["group_category_id"] == group_category_id
        ):
            group_id = group["id"]
            name = group["name"]
            members = get_members(group_id)
            result.append((group_id, name, members))
    return result


def get_roster():
    """
    Retrieves the complete roster of the course from Canvas
    including all students, instructors, TAs, etc.

    We use `courses/{config.canvas_course_id}/users` instead
    of `/courses/{course_id}/enrollments` (which has a role
    field) since we still need to specify graders/non-graders
    among instructors/TAs/etc. manually in `config.py`

    Returns a mapping of {NUID => Person objects}
    """
    roster = {}
    path = f"courses/{config.canvas_course_id}/users"
    users = get_canvas_data(path)

    for user in users:
        canvas_id = user["id"]
        if "login_id" in user:
            canvas_login = user["login_id"]
        else:
            canvas_login = None
        nuid = user["sis_user_id"]
        name = user["sortable_name"]  # format: "last, first"
        if "email" in user:
            email = user["email"]
        else:
            email = None
        p = Person(nuid, name, canvas_id, canvas_login, email)
        roster[p.nuid] = p
    return roster


def get_groups(roster):
    """
    Returns a list of Group objects from Canvas based on
    the Group Set name in the configuration file.  If no such
    group set exists or if an individual is not part of a group,
    they are placed in a group of one.

    In addition, each Person object in the given roster is
    associated with its group.
    """
    groups = []
    if config.grading_group_name is not None:
        group_data = get_group_tuples(config.grading_group_name)
        for gd in group_data:
            # (group_id,"groupName",[membersCanvasIds])
            g = Group(gd[0], gd[1])
            members = []
            for member_canvas_id in gd[2]:
                for nuid, p in roster.items():
                    if p.canvas_id == member_canvas_id:
                        members.append(p)
                        p.group = g
                        break
            g.add_members(members)
            groups.append(g)

    # iterate through roster and create groups of one:
    for nuid, p in roster.items():
        # but only if they have not already been assigned to a group
        if p.group is None:
            g = Group()
            g.add_members([p])
            p.group = g
            groups.append(g)
    return groups


def get_sections():
    """
    Retrieves all sections from the canvas course.  You can futher use

    https://canvas.unl.edu/api/v1/sections/{sectionId}/enrollments

    to get enrollments only for that section (which will include
    teachers, TAs, etc. but will have a `role` field to determine what
    it is.
    """
    path = f"/courses/{config.canvas_course_id}/sections"
    result_data = get_canvas_data(path)
    return result_data


def get_grade(assignment_id, user_id):
    """
    Returns the grade (generally a float) from the canvas gradebook
    for the given assignment and user.  If no grade has been assigned,
    the result will be None.
    """
    path = f"/courses/{config.canvas_course_id}/assignments/{assignment_id}/submissions/{user_id}"
    result_data = get_canvas_data(path)
    if "score" in result_data:
        return result_data["score"]
    return None

def get_grade_comments(assignment_id, user_id):
    """
    Returns any comment(s) included in an assignment submission for
    the given `assignment_id`/`user_id`.  Returns an empty list if no
    comments.
    """
    path = f"/courses/{config.canvas_course_id}/assignments/{assignment_id}/submissions/{user_id}"
    include = {"include": "submission_comments"}
    result_data = get_canvas_data(path, include)
    result = []
    # we still explicitly check that the comments are included
    if 'submission_comments' in result_data:
        #pull only the comment text for each one
        for c in result_data['submission_comments']:
            result.append(c['comment'])
    return result


def set_grade(assignment_id, user_id, score=None, comment=None):
    """
    Sets/updates the grade in the canvas gradebook for the given
    assignment (`assignment_id`) for the given user (`user_id`).
    If the comment is specified, canvas *adds* the comment and
    does not modify or delete any old one(s).  If the
    score/comment are `None`, they are ignored which allows you
    to change the score with no comment, add a comment without
    changing the score, or doing both.  If both are `None` this
    function does nothing.
    """
    if score is None and comment is None:
        return
    url = f"https://canvas.unl.edu/api/v1/courses/{config.canvas_course_id}/assignments/{assignment_id}/submissions/{user_id}"

    data = {}
    if score is not None:
        data["submission"] = {"posted_grade": score}
    if comment is not None:
        data["comment"] = {"text_comment": comment}
    requests.put(url, params=DEFAULT_PARAMS, json=data, timeout=10)
    return


def get_assignment_group_d(assignment_group_name):
    """
    Retrieves the canvas assignment group ID for the assignment group with the
    given name.  Returns None if no such assignment group exists.

    """
    groups = get_assignment_groups()
    groups = {i for i in groups if groups[i] == assignment_group_name}
    return next(iter(groups), None)


def get_assignment_groups():
    """
    Retrieves all assignment groups from canvas.  Returns a map of IDs
    to group names:

      {id -> name}

    """
    path = f"/courses/{config.canvas_course_id}/assignment_groups"
    data = get_canvas_data(path)
    assignment_groups = {}
    for r in data:
        assignment_group_id, name = r["id"], r["name"]
        assignment_groups[assignment_group_id] = name
    return assignment_groups


def get_assignments_in_group(group_id):
    """
    Retrieves all assignments from an assignment group (for the given `group_id`)
    from canvas.  Returns a list of assignments.

    """
    path = f"/courses/{config.canvas_course_id}/assignment_groups/{group_id}/assignments"
    result_data = get_canvas_data(path)
    assignments = []
    for r in result_data:
        a = Assignment(r["id"], r["name"], r["points_possible"])
        assignments.append(a)
    return assignments


def get_assignments(name=""):
    """
    Retrieves assignments from the canvas course.  By default, retrieves
    all assignments.  If the `name` parameter is provided, only assignments
    matching the name (even partially) will be included.

    Always returns a list regardless of the number of matched assignments.
    """
    path = f"/courses/{config.canvas_course_id}/assignments"
    params = {"search_term": name}
    result_data = get_canvas_data(path, params)
    assignments = []
    for r in result_data:
        a = Assignment(r["id"], r["name"], r["points_possible"])
        assignments.append(a)
    return assignments
