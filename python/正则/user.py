#!/usr/bin/env python3

import re

"""
验证用户名是否正确
用户名必须由字母、数字或下划线构成且长度在6~20个字符之间
"""
def testUsername():
    reg = r'^[0-9A-Za-z_]{6,20}$'
    while True:
        username = input('input username:')
        if 'quit' == username:
            break
        txt = re.match(reg, username)
        if not txt:
            print('验证错误')
        else:
            print('验证正确')
    print('程序结束...')

if __name__ == '__main__' :
    testUsername()
