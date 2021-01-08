
class Person:
    nuid = None
    name = None
    canvasId = None
    canvasLogin = None
    canvasEmail = None      
    cseLogin = None
    group = None
    
    def __init__(self, nuid,
                       name = None,
                       canvasId = None,
                       canvasLogin = None,
                       canvasEmail = None,
                       cseLogin = None):
        self.nuid        = nuid
        self.name        = name
        self.canvasId    = canvasId
        self.canvasLogin = canvasLogin
        self.canvasEmail = canvasEmail      
        self.cseLogin    = cseLogin
        
    def __str__(self):
        return "%-25s (%s) %-15s %s"%(self.name,self.nuid,self.cseLogin,self.canvasEmail)

    def __hash__(self):
        return hash(self.nuid)
        
    def __eq__(self,other):
        return self.nuid == other.nuid
        
    def __lt__(self,other):
        return (self.nuid < other.nuid)

    def toCsv(self):
        return "%s,%s,%s,%s,%s,%s\n"%(self.nuid,self.name,self.canvasId,self.canvasEmail,self.canvasLogin,self.cseLogin)
