from zipfile import ZipFile 
from config import config
import os

# Returns a map of 
#   { (fullPath,fileName,extension) => fileContents }
# of all files in the specified path according to config.fileExtensions
# If the configuration includes jar or zip files, it extracts
# files within the archive files but only one level deep
#
# The fullPath is included to ensure that duplicate files are not
# clobbered
def getFiles(path):
  files = {}
  if not os.path.exists(path):
    return files
  else:
    (_, _, filenames) = next(os.walk(path))
  if config.fileExtensions:
    filenames = [ f for f in filenames if f.endswith(tuple(config.fileExtensions)) ]
  if filenames:
    for fileName in filenames:
      (_,extension) = os.path.splitext(fileName)
      extension = extension[1:] #chomp period: .c -> c
      if extension == 'jar' or extension == 'zip':        
        extracted = extractArchiveFiles(fileName)
        files = {**files, **extracted}
      else:
        contents = open(path+fileName, errors='ignore').read()
        #if the file is empty, add content to accommodate codepost's API
        if not contents:
          contents = "EMPTY FILE"
        files[(path+fileName,fileName,extension)] = contents
  return files

# Extracts files from the given archive file (.tar, .zip), returning a map:
#  { (fullPath,fileName,extension) => fileContents }
# of all of them
def extractArchiveFiles(fileName):
  files = {}
  with ZipFile(fileName, 'r') as zip: 
    for z in zip.infolist():
      if not config.fileExtensions or (z.filename.endswith(tuple(config.fileExtensions)) and not z.filename.endswith(tuple(['.jar', '.zip'])) ):
          (path,basename) = os.path.split(z.filename)
          (_,extension) = os.path.splitext(basename)
          contents = zip.read(z).decode()
          files[(z.filename,basename,extension)] = contents
  return files
