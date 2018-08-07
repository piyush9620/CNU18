import requests
import hashlib
import os 
from kubernetes.client.rest import ApiException
SERVER_HOST=os.environ['SERVER_HOST']
SERVER_PORT=os.environ['SERVER_PORT']
r = requests.get("http://"+SERVER_HOST+":"+SERVER_PORT+"/generateFile")
resp = r.json()
print(resp)
checksum = resp["checksum"]
filename = resp["file"] 
r1 = requests.get("http://"+SERVER_HOST+":"+SERVER_PORT+"/static/"+filename)
chars = r1.text
md5_returned = hashlib.md5(chars.encode('utf-8')).hexdigest()
print(md5_returned==checksum)
from kubernetes import client, config
import pprint
pp = pprint.PrettyPrinter(indent=4)
# Configs can be set in Configuration class directly or using helper utility
config.load_kube_config()

v1 = client.CoreV1Api()
namespace = 'cnu2018-dev' # str | object name and auth scope, such as for teams and projects

pretty = 'pretty_example' # str | If 'true', then the output is pretty printed. (optional)
name = "harsh-arya-map"
body  = {
            "kind": "ConfigMap",
            "apiVersion": "v1",
            "metadata": {
                "name": name,
            },
            "data": {
                "random" : chars,
                "checksum":checksum
            }
        }
try: 
    api_response = v1.patch_namespaced_config_map(name = name,namespace = namespace, body = body, pretty=pretty)
    pp.pprint(api_response)
except ApiException as e:
    print("Exception when calling CoreV1Api->create_namespaced_endpoints: %s\n" % e)