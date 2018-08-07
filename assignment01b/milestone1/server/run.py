from flask import Flask  ,request, redirect, url_for, send_from_directory, jsonify
app = Flask(__name__)

@app.route("/generateFile")
def generateFile():
	import random
	import string
	import uuid
	import hashlib    
	chars = "".join( [random.choice(string.ascii_letters) for i in range(1024)] )
	filename = str(uuid.uuid4())+".txt"
	with open("static/"+filename, "w") as text_file:
		text_file.write(chars)
	with open("/serverdata/"+filename,"w") as text_file:
		text_file.write(chars)
	md5_returned = hashlib.md5(chars.encode('utf-8')).hexdigest()
	resp = {"file":filename,"checksum":md5_returned}
	return jsonify(resp)

@app.route('/static/<path:path>')
def static_proxy(path):
  # send_static_file will guess the correct MIME type
  return app.send_static_file(path)

if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0')
