import codepost
from config import config
from course import course

codepost.configure_api_key(config.codePostApiKey)

def addRubric(assignmentId, categories, categoryComments):
    for i,(name,points) in enumerate(categories):
        my_rubric_category = codepost.rubric_category.create(
          assignment=assignmentId,
          name=name,
          pointLimit=points,
          sortKey=i)
#        for j,(text,pointDelta) in enumerate(categoryComments[name]):
#            my_rubric_comment = codepost.rubric_comment.create(
#              text=text,
#              pointDelta=pointDelta,
#              category=my_rubric_category.id,
#              sortKey=j)
  
# graders and students are only valid if they have cse logins
# alternatively, we could use the email address from Canvas but
# students may have opted out of sharing this and it would not
# be available from the API.  

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


# CSCE 156
# Assignment setup
# 7 code-based assignments
# point totals: (100+125+175+135+100+145+110) = 890

rubricCategories = [
  [('Style/Documentation', 5), ('Design', 10), ('Test Cases', 10), ('Correctness', 75)], 
  [('Style', 10), ('Documentation', 10), ('Design', 30), ('Test Cases', 15), ('Correctness', 60), ('Bonus/Honors', 0)], 
  [('Style', 10), ('Documentation', 10), ('Design', 65), ('Test Cases', 15), ('Correctness', 75), ('Bonus/Honors', 0)], 
  [('Style', 10), ('Documentation', 5), ('Design', 50), ('Test Cases', 20), ('Correctness', 50), ('Bonus/Honors', 0)], 
  [('Style', 10), ('Documentation', 5), ('Design', 35), ('Correctness', 50), ('Bonus/Honors', 0)], 
  [('Style', 10), ('Documentation', 10), ('Design', 50), ('Correctness', 75), ('Bonus/Honors', 0)], 
  [('Style', 5), ('Documentation', 5), ('Design', 60), ('Correctness', 40), ('Bonus/Honors', 0)], 
]

for i,categories in enumerate(rubricCategories):
  total = sum([x[1] for x in categories])
  a = codepost.assignment.create(
    name="Assignment "+str(i+1),
    points=total,
    course=config.codePostCourseId,
    sortKey=i)
  addRubric(a.id, 
    categories, 
    {})

updateRoster()
