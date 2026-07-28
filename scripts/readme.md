
This is a collection of python scripts used to pull/process
data from Canvas and the SoC handin server (`nuros.unl.edu`)
to facilitate grading.  

# Setup

## Edit the `config.py` file

1. Enter your Canvas API key and course ID:
  - Click your account in the left navigation, then settings
  - Click "New Access Token"
  - Copy that to the `canvas_api_key` variable
  - Copy your Canvas course ID into the `canvas_course_id` variable
    This can be found from the URL for your course, for example
    if your course's URL is https://canvas.unl.edu/courses/12345
    then your course ID is 12345

2. Update the remaining configurations for your course including
the `handin_directory` and `file_extensions` variables, the
group-related variables (if you use canvas groups for group
assignments) and the `instructor_nuids` and `grader_nuids` lists to
include all relevant NUIDs (as strings).  

## Running

Each script can be run using `python`:

- `course.py` will load all data from Canvas and print a
  roster.  It will also print a raw list of email addresses if you
  need them for (say) Piazza or other communication tools.
