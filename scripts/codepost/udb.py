import mariadb
import sys
import pickle
import os.path
from config import config

def mapNuidsToCseLogins(nuidToCseLogin, pickleFileName = None):
  """
    Maps NUIDs to CSE login IDs.  Given the map of NUIDs
    (some of which may already be mapped) it updates the
    map by querying the udb (User Database) on the cse-apps
    server, including any missing logins in the returned
    map.

    If the specified pickleFileName is provided, the method
    first tries to load (but does not overwrite) any missing
    logins from the serialized pickle file to avoid going
    to the database (a database query is still made for any
    remaining missing records).  Further, the resulting
    map is persisted back to the pickle file (overwriting
    the original) before it is returned.

    @param nuidToCseLogin: the given map of (possibly incomplete) NUIDs-to-CSE Logins
    @param pickleFileName: the name of the pickle file to persist from/to
    @return: an updated NUID-to-CSE Login map
  """
  if pickleFileName is not None:
    if os.path.isfile(pickleFileName):
      print('loading from data file '+pickleFileName+'...')
      f = open(pickleFileName, "rb")
      nuidToCseLoginPickle = pickle.load(f)
      f.close()
      for nuid,login in nuidToCseLoginPickle.items():
        if nuid in nuidToCseLogin and  nuidToCseLogin[nuid] is None:
          nuidToCseLogin[nuid] = login

  # generate a list of missing NUIDs
  missingLogins = []
  for nuid,login in nuidToCseLogin.items():
    if login is None:
      missingLogins.append(nuid)

  # if missing logins, try to get them from the DB:
  if missingLogins:
    pairs = getLoginsByNuid(missingLogins)
    for (nuid,login) in pairs:
      nuidToCseLogin[nuid] = login

  if pickleFileName is not None:
    outFile = open(pickleFileName, 'wb')
    pickle.dump(nuidToCseLogin, outFile)
    outFile.close()

  return nuidToCseLogin

def getLoginsByNuid(nuids):
  """
  Returns a list of tuples of (nuid,login) pairs corresponding
  to the given nuids.
  """
  try:
    conn = mariadb.connect(
      user=config.udb.username,
      password=config.udb.password,
      host=config.udb.host,
      port=3306,
      database=config.udb.database
    )
  except mariadb.Error as e:
    print(f"Error connecting to MariaDB Platform: {e}")
    sys.exit(1)

  parameterStr = ','.join(['%s'] * len(nuids))
  cursor = conn.cursor()
  result = []
  try:
    cursor.execute(
      "SELECT login,nuid FROM view_login_nuid where nuid in (%s)"%parameterStr,
      tuple(nuids)
    )
    for (login,nuid) in cursor:
      result.append( (nuid, login) )
    return result
  except mariadb.Error as e:
    print(f"Error connecting to MariaDB Platform: {e}")
    sys.exit(1)

def udbConnTest():
  """
  Makes a connection to UDB for troubleshooting.
  """
  pairs = getLoginsByNuid(['12345678', '35140602'])
  for (nuid,login) in pairs:
      print(nuid,"->",login)

if __name__ == "__main__":
    udbConnTest()
