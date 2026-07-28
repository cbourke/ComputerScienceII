"""
Pulls all data from codepost.io and canvas and gives a
summary report (on codepost.io course info) and reports
any inconsitencies in the roster
"""
import codepost
from course import course
from config import config

codepost.configure_api_key(config.codepost_api_key)

codepost_course = codepost.course.retrieve(id=config.codepost_course_id)

print('Codepost.io Course: ')
print(f'Id     = {str(codepost_course.id)}')
print(f'Name   = {codepost_course.name}')
print(f'Period = {codepost_course.period}')

print(f'Assignments ({str(len(codepost_course.assignments))}): ')
for a in codepost_course.assignments:
    print(f'{a.name:20s} (id={a.id})')

roster = codepost.roster.retrieve(id=config.codepost_course_id)
graders = list()
students = list()

print(f'Admins ({len(roster.courseAdmins)})')
for a in roster.courseAdmins:
    print(f'  {a}')
    graders.append(a)
print(f'Graders ({len(roster.graders)})')
for g in roster.graders:
    print(f'  {g}')
    graders.append(g)
graders = list(set(graders))
print(f'Students ({len(roster.students)})')
for s in roster.students:
    print(f'  {s}')
    students.append(s)

# check that codepost graders are in canvas class
print('Checking Instructors...')
for nuid, instructor in course.instructors.items():
    if instructor.canvas_email not in graders:
        print(f'Instructor {instructor} not in codepost')
    else:
        graders.remove(instructor.canvas_email)
if graders:
    print('Instructors in codepost missing in canvas:')
    for g in graders:
        print(f'  {g}')

# check that codepost students are in canvas class
print('Checking Students...')
for nuid, student in course.students.items():
    if student.canvas_email not in students:
        print(f'Student {student} not in codepost')
    else:
        students.remove(student.canvas_email)
if students:
    print('Students in codepost missing in canvas:')
    for s in students:
        print(f'  {s}')
