#!/usr/bin/env python3
import json

def readJson(file_name):
    with open(file_name, 'r', encoding='utf-8') as f:
        di = json.load(f)
        print(di)
        print()
        print('-----------------------')
        print()
        s = json.dumps(di)
        print(s)

if __name__ == '__main__':
    readJson('./data.json')
