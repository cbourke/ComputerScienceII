import argparse
import sys
import codepost
from config import config

parser = argparse.ArgumentParser()

parser.add_argument('--codePostApiKey',
                    type=str,
                    default=config.codePostApiKey,
                    help='the codepost API key to use, default is to use the one deinfed in config.py')
parser.add_argument('--codePostCourseId',
                    type=int,
                    default=config.codePostCourseId,
                    help='specify the ID of the codepost course to use, default is to use the one deinfed in config.py')
parser.add_argument("codepostAssignmentId", help=
  """The codepost.io assignment ID number (a run of
  codepostListCourseInfo.py or codepostValidateCourse.py
  may be necessary to find this)
  """, type=int)
parser.add_argument('emails', metavar='EmailA EmailB EmailC', type=str, nargs=3,
                    help='Emails of the students for A, B, C examples')
args = parser.parse_args()
# dev script for ABET retrieval of A-B-C materials

#pandoc to PDF: pandoc -s -V geometry:margin=1in -o documentation.pdf part01.md
# Given:
#  course id
#  assignment ID
#  A - email Id
#  B - email Id
#  C - email Id
#
# retrieve: all source files, comments (score?)
#  build: markdown with assessment + score + comments

# dev: CSCE 156 - spring 2021 = 1556
# dev: Assignment 1: 7880
# dev: 7880 "amonson3@huskers.unl.edu", "aoshiro3@huskers.unl.edu", "apalmesano@huskers.unl.edu"

# Load from config.py, allow CLA override
codePostApiKey = args.codePostApiKey
assignmentId = args.codepostAssignmentId
# we'll make this into a fancy map
emails = { "A" : args.emails[0],
           "B" : args.emails[1],
           "C" : args.emails[2]}

codepost.configure_api_key(codePostApiKey)


def getAssignmentReport(assignmentId):
    """
    Produces an ABET assignment report for the specified assignment
    """
    return ""

#roster = codepost.roster.retrieve(id=courseId)
#TODO: check if emails are in roster?
ass = codepost.assignment.retrieve(id=assignmentId)
courseId = ass.course.id
course = codepost.course.retrieve(id=courseId)
courseName = course.name
coursePeriod = course.period
assignmentName = ass.name
assignmentPts = ass.points
assignmentMean = ass.mean
assignmentMedian = ass.median

summary = f"""
# {courseName} - {coursePeriod}
## {assignmentName}
  * Points: {assignmentPts}
  * Mean: {assignmentMean}
  * Median: {assignmentMedian}\n\n"""
content = ""

for g, email in emails.items():
  studentSubmission = ass.list_submissions(student=email)
  # we only expect 1 submission since it is scripted
  if len(studentSubmission) != 1:
      print("WARNING: number of submissions is " + str(len(studentSubmission)) + " for student " + email + " using the first from the API", file = sys.stderr)
  submission = studentSubmission[0]
  submissionId = submission.id
  grade = submission.grade
  percent = 100.0 * grade / assignmentPts
  # fileId is a file object but lazily loaded so only the
  # .id is available, the the whole file:
  summary += f"""
## {g} Example
  * Student: {email}
  * Score: {grade:.1f} / {assignmentPts:.1f} = {percent:.2f}%
"""
  for fileId in submission.files:
    f = codepost.file.retrieve(id=fileId.id)
    fileName = f.name
    fileContents = f.code.encode('latin1', 'backslashreplace').decode('unicode-escape')
    fileExtension = f.extension
    fileGraderCommentIds = [x.id for x in f.comments]
    summary += f"  * Source File: `{fileName}`\n"
    content += f"## {g} Example - `{fileName}`\n"
    content += f"```{fileExtension}\n{fileContents}\n```\n"
    for commentId in fileGraderCommentIds:
        c = codepost.comment.retrieve(id=commentId)
        summary += f"    * Lines {c.startLine:d} - {c.endLine:d} (-{c.pointDelta:.1f}): {c.text:s}\n"

print(summary)
print(content)
