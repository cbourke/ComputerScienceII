"""
This is the module loads basic data on the course from canvas including
a full roster (including students, TAs, instructors) and assignment
groups.

"""

from canvas_utils import get_roster
from canvas_utils import get_groups

# {"NUID" => Person}
roster = get_roster()
# [Group]
groups = get_groups(roster)

if __name__ == "__main__":
    for nuid,p in roster.items():
        print(p)
