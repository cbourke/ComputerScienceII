import codepost
from config import config

codepost.configure_api_key(config.codePostApiKey)

# TODO
# 1. Update Canvas Scripts to pull groups for paired assignments
# 2. Update grading assignments to use groups
# 3. Update codepost scripts to extract .java files from .jar file
# 