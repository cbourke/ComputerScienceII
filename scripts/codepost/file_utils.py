"""
File utility methods
"""

import base64
import os
from zipfile import ZipFile

from config import config


def get_files(path):
    """
    Returns a map of

      { (fullPath,file_name,extension) => fileContents }

    of all files in the given path according to config.file_extensions
    If the configuration includes jar or zip files, it extracts
    files within the archive files but only one level deep (it does
    not recursively extract archives within archives).

    The key consists of a triple: the fullPath is included to ensure
    that duplicate files are not excluded from the map; the file_name
    and extension (c, md, java for example, sans period) are included
    for convienience.  The value in the map is the contents of the file
    with any non-decodable characters excluded.

    path -- the path in which to retrieve files
    """
    files = {}
    if not os.path.exists(path):
        return files
    (_, _, filenames) = next(os.walk(path))
    if config.file_extensions:
        filenames = [f for f in filenames if f.endswith(tuple(config.file_extensions))]
    if filenames:
        for file_name in filenames:
            (_, extension) = os.path.splitext(file_name)
            if extension[0] == ".":
                extension = extension[1:]  # chomp period: .c -> c
            if extension in ("jar", "zip"):
                extracted = extract_archive_files(path + file_name)
                files = {**files, **extracted}
            elif extension == "png":
                encoded = base64.b64encode(open(path + file_name, "rb").read())
                contents = "data:data/image;base64," + encoded.decode("utf-8")
                files[(path + file_name, file_name, extension)] = contents
            else:
                contents = open(path + file_name, errors="ignore").read()
                # if the file is empty, add content to accommodate codepost's API
                if not contents:
                    contents = "EMPTY FILE"
                # If the file contains a null byte, likely it is binary,
                # we ignore its contents.  In order to support binary files
                # additional support along with an appropriate media (MIME)
                # type prefix tag;
                #see https://www.iana.org/assignments/media-types/media-types.xhtml
                elif "\0" in contents:
                    contents = "BINARY"
                files[(path + file_name, file_name, extension)] = contents
    return files


def extract_archive_files(file_name):
    """
    Extracts files from the given archive file (.tar, .zip), returning a map:

      { (fullPath,file_name,extension) => fileContents }

    of all of them according to the config.file_extensions similar to
    getFiles(path)

    file_name -- the path/file of the archive file to extract from
    """
    files = {}
    with ZipFile(file_name, "r") as zip:
        for z in zip.infolist():
            if not config.file_extensions or (
                z.filename.endswith(tuple(config.file_extensions))
                and not z.filename.endswith(tuple([".jar", ".zip"]))
            ):
                (path, basename) = os.path.split(z.filename)
                (_, extension) = os.path.splitext(basename)
                contents = zip.read(z).decode(errors="ignore")
                if not contents:
                    contents = "EMPTY FILE"
                elif "\0" in contents:
                    contents = "BINARY"
                files[(z.filename, basename, extension)] = contents
    return files
