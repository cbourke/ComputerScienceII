import pickle
import sys
import csv
from config import config

"""
Manually loads a map of NUID to CSE login IDs to the 
pickle file specified in the cofiguration file.  The
CSV file is provided as a command line argument and
should consist of nuid,cselogin rows.

This utility is used if and when programatic access to
udb (user Database) on cse-apps is unavaialable or has
to manually be accessed.  To dump via command line you 
can load the query:

SELECT nuid,login FROM view_login_nuid INTO OUTFILE '/path/to/file.csv'

into an sql file (say foo.sql) and then use:

mysql -u user -ppasswd -h cse-apps.unl.edu udb < foo.sql | sed 's/\t/,/g' > out.csv

For some reason exporting to a CSV file has been denied by 
the database (but CLI access is perfectly kosher?).

"""

nuidToCseLogin = {}

with open(sys.argv[1], mode='r') as csv_file:
  csv_reader = csv.DictReader(csv_file)  
  line_count = 0
  for row in csv_reader:
    nuid = row['nuid']
    login = row['login']
    nuidToCseLogin[nuid] = login
    

pickleFileName = config.nuidToCseLoginPickle

outFile = open(pickleFileName, 'wb')
pickle.dump(nuidToCseLogin, outFile)
outFile.close()
