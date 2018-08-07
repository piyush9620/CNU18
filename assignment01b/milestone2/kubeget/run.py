from kubernetes import client, config
import pprint
import json
from flask import Flask  ,request, redirect, url_for, send_from_directory, jsonify
app = Flask(__name__)
config.load_kube_config()

@app.route("/")
def generate():
	v1 = client.CoreV1Api()
	namespace = 'cnu2018-dev' # str | object name and auth scope, such as for teams and projects
	pretty = 'pretty_example' # str | If 'true', then the output is pretty printed. (optional)
	name = "harsh-arya-map"
	api_response = v1.read_namespaced_config_map(name = name,namespace = namespace, pretty=pretty)
	print(str(api_response))
	return jsonify(api_response.data)

if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0')


