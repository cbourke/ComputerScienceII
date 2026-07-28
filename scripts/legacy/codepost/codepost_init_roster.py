"""
Methods to initialize and/or update a roster in codepost.io
"""
import codepost
from config import config
from course import course

codepost.configure_api_key(config.codepost_api_key)

def update_roster():
    """
    Initializes or updates the roster in codepost.io based
    on the roster in canvas as well as the graders/instructors
    specified in `config.py`.

    `codepost_validate_course.py` should be run to get a report
    of unsynched data before running this script.
    """
    grader_emails = []
    student_emails = []
    instructor_emails = []

    for nuid,instructor in course.instructors.items():
        if instructor.canvas_email:
            instructor_emails.append(instructor.canvas_email)
        else:
            print(f'WARNING, instructor {instructor} has no email')

    for nuid,grader in course.graders.items():
        if grader.canvas_email:
            grader_emails.append(grader.canvas_email)
        else:
            print(f'WARNING, grader {grader} has no email')

    for nuid,student in course.students.items():
        if student.canvas_email:
            student_emails.append(student.canvas_email)
        else:
            print(f'WARNING, student {student} has no email')

    codepost.roster.update(
      id=config.codepost_course_id,
      students=student_emails,
      graders=grader_emails,
      superGraders=grader_emails,
      courseAdmins=instructor_emails)

update_roster()
