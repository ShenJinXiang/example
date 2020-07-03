#!/usr/bin/env python3

import re

"""
验证QQ号是否正确
QQ号是5~12的数字且首位不能为0
"""
def testQQ():
    reg = r'^[1-9]\d{4,11}$'
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
    testQQ()
