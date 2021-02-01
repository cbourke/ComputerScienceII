"""
Master configuration file for all scripts.  

This configuration file will contain sensitive information
(NUIDs, passwords) and should *not* be tracked or committed
to a remote repository (even a private one).  If changes
do need to be made, sensitive information should be removed
before hand.  

To help avoid committing sensitive information, you should
set this file to be untracked: 
   git update-index --assume-unchanged config.py
To track the file again you can use:
   git update-index --no-assume-unchanged config.py

"""
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
        
    # CSE Handin Path - this needs to be an *absolute* path
    # since the script may run without the HOME enviro variable
    handinDirectory = "/home/grad/Classes/cse156/handin/"
    
    # CSE User Database configuration (udb on cse-apps)
    # cse-apps.unl.edu is firewalled for 3306 except from 
    # cse/csce
    #  - The pickle file is used to locally store database information
    #    so that it doesn't have to requery for every script execution 
    #  - Credentials must be obtained from CSE System Administration
    nuidToCseLoginPickle = "nuidToCseLogin.pkl"
    class udb:
        driver   = "libmaodbc"
        host     = "cse-apps.unl.edu"
        database = "udb"
        username = ""
        password = ""

    # To use canvas groups for assigning grading, set the name 
    # of the group here.  
    gradingGroupName = "Project Pairs"

    # Course-specific role configurations
    #  Instructors are given codepost admin access, but not assigned to grade
    #  Graders are given "super grader" access to codepost and assigned to 
    #   grade.  An instructor must also be a grader if you want them to be
    #   assigned grading duties.
    instructorNuids = ["35140602", # Bourke
                      ]
    # All graders' NUIDs
    graderNuids = ["35140602", # Bourke
                  ]

config = Config()
