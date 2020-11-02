#!/usr/bin/env python3

try:
    num = int(input('input number:'))
    print('number:', num)
except Exception as err:
    print("error:", err)