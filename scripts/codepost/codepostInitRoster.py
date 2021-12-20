import codepost
from config import config
from course import course

codepost.configure_api_key(config.codePostApiKey)

def updateRoster():
    graderEmails = []
    studentEmails = []
    instructorEmails = []

    for nuid,instructor in course.instructors.items():
        if instructor.canvasEmail:
            instructorEmails.append(instructor.canvasEmail)
        else:
            print("WARNING, instructor %s has no cse login/email"%instructor)

    for nuid,grader in course.graders.items():
        if grader.canvasEmail:
            graderEmails.append(grader.canvasEmail)
        else:
            print("WARNING, grader %s has no cse login/email"%grader)

    for nuid,student in course.students.items():
        if student.canvasEmail:
            studentEmails.append(student.canvasEmail)
        else:
            print("WARNING, student %s has no cse login/email"%student)

    codepost.roster.update(
      id=config.codePostCourseId,
      students=studentEmails,
      graders=graderEmails,
      superGraders=graderEmails,
      courseAdmins=instructorEmails)

updateRoster()
