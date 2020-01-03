import subprocess
import sys

# Solution described in TokenRefresher.java - getJsonToken method
curl = "curl --data \"grant_type=refresh_token" \
       "&client_id={}" \
       "&client_secret={}" \
       "&refresh_token={}\"" \
       " \"{}\"".format(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4],)

subprocess.call(curl, shell=True)
