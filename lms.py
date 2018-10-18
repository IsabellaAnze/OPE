from flask import Flask, jsonify, request
import json
import urllib.request
import random

app = Flask(__name__)

disciplinas = [{"id": e, "nome": "Produto "+str(e), "tipo":"Tipo "+str(e), "foto":"https://s3-sa-east-1.amazonaws.com/portfolio-usuario/fullsize%2F2017%2F04%2F13%2F404736_005515476_1523457946.png", "quantidade": "Quantidade "+str(e)} for e in range(1,11)]   

@app.route("/aquarela", methods=['GET'])
def get():
    return jsonify(aquarela)

@app.route("/aquarela/<int:id>", methods=['GET'])
def get_one(id):
    filtro = [e for e in aquarela if e["id"] == id]
    if filtro:
        return jsonify(filtro[0])
    else:
        return jsonify({})

@app.route("/aquarela", methods=['POST'])
def post():
    global aquarela
    try:
        content = request.get_json()

        # gerar id
        ids = [e["id"] for e in aquarela]
        if ids:
            nid = max(ids) + 1
        else:
            nid = 1
        content["id"] = nid
        disciplinas.append(content)
        return jsonify({"status":"OK", "msg":"produto adicionada com sucesso"})
    except Exception as ex:
        return jsonify({"status":"ERRO", "msg":str(ex)})

@app.route("/aquarela/<int:id>", methods=['DELETE'])
def delete(id):
    global aquarela
    try:
        aquarela = [e for e in aquarela if e["id"] != id]
        return jsonify({"status":"OK", "msg":"produto removida com sucesso"})
    except Exception as ex:
        return jsonify({"status":"ERRO", "msg":str(ex)})

@app.route("/push/<string:key>/<string:token>", methods=['GET'])
def push(key, token):
	d = random.choice(aquarela)
	data = {
		"to": token,
		"notification" : {
			"title":d["nome"],
			"body":"Você tem novo produto em "+d['nome']
		},
		"data" : {
			"aquarelaId":d['id']
		}
	}
	req = urllib.request.Request('http://fcm.googleapis.com/fcm/send')
	req.add_header('Content-Type', 'application/json')
	req.add_header('Authorization', 'key='+key)
	jsondata = json.dumps(data)
	jsondataasbytes = jsondata.encode('utf-8')   # needs to be bytes
	req.add_header('Content-Length', len(jsondataasbytes))
	response = urllib.request.urlopen(req, jsondataasbytes)
	print(response)
	return jsonify({"status":"OK", "msg":"Push enviado"})


if __name__ == "__main__":
    app.run(host='0.0.0.0')