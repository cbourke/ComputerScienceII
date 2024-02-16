import codepost
from config import config
from course import course

codepost.configure_api_key(config.codepost_api_key)

def updateRoster():
    """
    Initializes or updates the roster in codepost.io based
    on the roster in canvas as well as the graders/instructors
    specified in config.py.

    `codepostValidateCourse.py` should be run to get a report
    of unsynched data before running this script.
    """
    graderEmails = []
    studentEmails = []
    instructorEmails = []

    for nuid,instructor in course.instructors.items():
        if instructor.canvas_email:
            instructorEmails.append(instructor.canvas_email)
        else:
            print("WARNING, instructor %s has no email"%instructor)

    for nuid,grader in course.graders.items():
        if grader.canvas_email:
            graderEmails.append(grader.canvas_email)
        else:
            print("WARNING, grader %s has no email"%grader)

    for nuid,student in course.students.items():
        if student.canvas_email:
            studentEmails.append(student.canvas_email)
        else:
            print("WARNING, student %s has no email"%student)

    codepost.roster.update(
      id=config.codepost_course_id,
      students=studentEmails,
      graders=graderEmails,
      superGraders=graderEmails,
      courseAdmins=instructorEmails)

updateRoster()
