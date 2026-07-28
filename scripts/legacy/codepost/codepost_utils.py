
from packaging import version
import codepost

from config import config

if version.parse(codepost.__version__) < version.parse("0.3.2"):
    print(f"WARNING using a legacy version of codepost: {codepost.__version__}")

codepost.configure_api_key(config.codepost_api_key)

def get_assignment_id(assignment_name):
    """
    Retrieves the codepost id (integer) of the given assignment (string)
    """
    codepost_course = codepost.course.retrieve(id=config.codepost_course_id)
    #generator comprehension
    return next( (a.id for a in codepost_course.assignments if a.name == assignment_name), None)

def save_file(path, name, contents):
    """
    Saves the `contents` to a file specified by the `path`/`name`
    """
    file_path = path + "/" + name
    f = open(file_path, "w")
    f.write(contents)
    f.close()
