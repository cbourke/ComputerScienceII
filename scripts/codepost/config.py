# Changes will not be tracked on this file to avoid committing
# keys and passwords, if other changes are made, it will need
# to be restored to the index
#   git update-index --no-assume-unchanged config.py
# and then removed again:
#   git update-index --assume-unchanged config.py
# 
class Config:
    
    # Canvas API configuration
    canvasUrl      = "https://canvas.unl.edu/api/v1/"
    canvasApiKey   = ""
    canvasCourseId = "" 
    
    # codepost.io configuration
    codePostApiKey = ""
    codePostCourseId = None 
    
    # file extensions/types to include in codepost push
    # leave empty to include all files
    fileExtensions = [".java", ".php", ".jar", ".sql"]
        
    # Relative CSE Handin Path
    # By default, these scripts are assumed to be in ~/codepost
    handinPath = "../handin/"
    
    # CSE User Database configuration (udb on cse-apps)
    # cse-apps.unl.edu is firewalled for 3306 except from cse/csce
    nuidToCseLoginPickle = "nuidToCseLogin.pkl"
    class udb:
        driver   = "libmaodbc"
        host     = "cse-apps.unl.edu"
        database = "udb"
        username = ""
        password = ""

    # Course-specific role configurations
    #  Instructors are given codepost admin access, but not assigned to grade
    #  Graders are given "super grader" access to codepost and assigned to 
    #   grade.  An instructor must also be a grader if you want them to be
    #   assigned grading duties.
    instructorNuids = ["35140602", # Bourke
                       ]
    graderNuids = ["35140602", # Bourke
                 ]

config = Config()
