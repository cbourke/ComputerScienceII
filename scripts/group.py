"""
Module/class to support groups in a course
"""

from operator import attrgetter

class Group:
    """
    A group may consist of one or more students and has a
    direct connection to a group on Canvas in which students
    are either assigned or self select into groups through
    the "people" page.  In the case that students are not
    assigned to a group, a Group object is still created for
    them as a "group of one".

    Codepost does not represent groups directly, however each
    submission may have more than one student assicated with it.
    """

    canvas_group_id = None
    canvas_group_name = None
    # This is an array of Persons
    members = []

    def __init__(self, canvas_group_id=None, canvas_group_name=None):
        self.canvas_group_id = canvas_group_id
        self.canvas_group_name = canvas_group_name

    def __str__(self):
        s = ''
        if len(self.members) > 1:
            s = f'  {self.canvas_group_name:<20s} ({self.canvas_group_id})\n'
            for member in self.members:
                s += f'    {str(member):<s}\n'
        else:
            s += f'  {str(self.members[0]):<s}\n'
        return s

    def __eq__(self, other):
        return self.members == other.members

    def __lt__(self, other):
        return self.members[0].name < other.members[0].name

    def add_members(self, members):
        """
        Adds the given members, sorting them by name
        """
        self.members = sorted(members, key=attrgetter("name"))
