#!/usr/bin/env python3

"""
水仙花数也被称为超完全数字不变数、自恋数、自幂数、阿姆斯特朗数，
它是一个3位数，该数字每个位上数字的立方之和正好等于它本身
"""


for num in range(100, 1000):
    x = num % 10
    y = num // 10 % 10
    z = num // 100 
    if num == x ** 3 + y ** 3 + z ** 3:
        print(num)

print('end...')

