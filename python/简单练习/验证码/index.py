#!/usr/bin/env python3
'''
生成指定长度字符串
大小写字母和数字组成
'''
import random

def generate_code(code_len = 4):
    strs = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'
    lens = len(strs)
    s = ''
    for i in range(code_len):
        s += strs[random.randint(0, lens - 1)]
    return s


if __name__ == '__main__':
    print(generate_code())
