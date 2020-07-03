#!/usr/bin/env python3

from urllib import request, parse
import json


def printContent(url):
    response = request.urlopen(url);
    print(response.read().decode('utf-8'))


def httpService(url, data=None, headers=None, method=None):
    if headers is None:
        headers = {}
    if data is not None:
        data = bytes(parse.urlencode(data), 'utf-8')
    req = request.Request(url, data=data, headers=headers, method=method)
    resp = request.urlopen(req)
    print(resp.read().decode('utf-8'))


def start():
    if __name__ == '__main__':
        urlstr = 'https://www.shenjinxiang.com'
        httpService(urlstr)


start()
