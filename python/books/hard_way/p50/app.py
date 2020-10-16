#!/usr/bin/env python3

from flask import Flask
from flask import render_template
from flask import request

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route("/info")
def info():
    str1 = 'Hello World!'
    return render_template("index.html", greeting=str1)

@app.route("/hello")
def hello():
    name = request.args.get("name", "World!")
    str1 = f'Hello {name}'
    return render_template('index.html', greeting=str1)

if __name__ == '__main__':
    app.run()