#!/usr/bin/env python3
'''
打印符号组成的三角形

Date: 2020-03-19
Author: 申锦祥
'''
num = 10
char = '*'

for n in range(1, num):
    print(char * n)

print('-------------------------------')

for n in range(1, num):
    print(' ' * (num - n), char * n)

print('-------------------------------')

for n in range(1, num):
    print(' ' * (num - n), char * (2 * n - 1))

print('-------------------------------')
