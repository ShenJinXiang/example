#!/usr/bin/env python3

import json

def load(file_name):
    try:
        with open(file_name, 'r', encoding='utf-8') as fs:
            di = json.load(fs)
            return di
    except FileNotFoundError:
        print('无法打开指定的文件!')
    except LookupError:
        print('指定了未知的编码!')
    except UnicodeDecodeError:
        print('读取文件时解码错误!')

def dump(file_name, di):
    try:
        with open(file_name, 'w', encoding='utf-8') as fs:
            json.dump(di, fs)
    except FileNotFoundError:
        print('无法打开指定的文件!')
    except LookupError:
        print('指定了未知的编码!')
    except UnicodeDecodeError:
        print('读取文件时解码错误!')


if __name__ == '__main__':
    di1 = {
            "name": "shenjinxiang",
            "age": 34,
            "address": "太原"
            }

    print('di:')
    print(di1)
    str1 = json.dumps(di1)
    print('str:')
    print(str1)

    di2 = json.loads(str1)
    print('di:')
    print(di2)

    dump('./info.json', di2)

    di3 = load('./info.json')
    print('di:')
    print(di3)
