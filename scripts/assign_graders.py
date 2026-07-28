"""
This script assigns graders to student submissions.  The
assignment is printed to the standard output and saved to
a specified CSV file.

Usage: python assign_graders.py [--output-file output_file.csv]

In detail:

 1. It loads the current roster from Canvas (and separates
    instructors/graders/students using the config.py params)
 2. It randomizes grading assignments (evenly distributing
    them among graders) and outputs an assignment report to
    the standard output and data to a CSV file.

"""
import argparse
import sys
import os
import codepost

from config import config
from course import course

parser = argparse.ArgumentParser()

parser.add_argument(
    "-o",
    "--output-file",
    default="grading_assignment.csv",
    help="The output file name (default: %(default)s)",
)

args = parser.parse_args()

output_file = args.output_file

grading_assignment = course.get_grading_assignment()
s = course.assignment_to_string(grading_assignment)
print(s)

csv = course.assignment_to_csv(grading_assignment)
f = open(f'{output_file}', 'w')
f.write(csv)
f.close()
