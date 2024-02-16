
class Person:
    """
    A class that models a person which includes students,
    graders, instructors, etc.

    Each person is uniquely identified by their NUID and
    is expected to have credentials Canvas.

    For students, their email is usually their huskers email.

    Separate name elements (first, last) may not be available in
    Canvas and instead only a 'display' name may be available due
    to students being able to set a 'preferred name' in the
    registration system.  Generally this should be sufficient
    for lexicographic ordering.
    """

    nuid = None
    name = None
    canvas_id = None
    canvas_login = None
    canvas_email = None
    group = None

    def __init__(self, nuid,
                       name = None,
                       canvas_id = None,
                       canvas_login = None,
                       canvas_email = None):
        self.nuid        = nuid
        self.name        = name
        self.canvas_id    = canvas_id
        self.canvas_login = canvas_login
        self.canvas_email = canvas_email

    def __str__(self):
        return "%-30s (%s) %s"%(self.name,self.nuid,self.canvas_email)

    def __hash__(self):
        return hash(self.nuid)

    def __eq__(self,other):
        """
        Equality and ordering is determined based only on NUID
        """
        return self.nuid == other.nuid

    def __lt__(self,other):
        """
        Equality and ordering is determined based only on NUID
        """
        return (self.nuid < other.nuid)

    def toCsv(self):
        return "%s,%s,%s,%s,%s\n"%(self.nuid,self.name.replace(", ", ","),self.canvas_id,self.canvas_email,self.canvas_login)
