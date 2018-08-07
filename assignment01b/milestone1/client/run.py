import requests
import hashlib
import os 
SERVER_HOST=os.environ['SERVER_HOST']
SERVER_PORT=os.environ['SERVER_PORT']
r = requests.get("http://"+SERVER_HOST+":"+SERVER_PORT+"/generateFile")
resp = r.json()
print(resp)
checksum = resp["checksum"]
filename = resp["file"] 
r1 = requests.get("http://"+SERVER_HOST+":"+SERVER_PORT+"/static/"+filename)
chars = r1.text
with open("/clientdata/"+filename, "w") as text_file:
		text_file.write(chars)
md5_returned = hashlib.md5(chars.encode('utf-8')).hexdigest()
print(md5_returned==checksum)