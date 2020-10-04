#!/usr/bin/env python3

import re


if __name__ == '__main__':
    phone_number_reg = re.compile(r'\d{3}-\d{3}-\d{4}')
    mo = phone_number_reg.search('My number is 415-555-4242. Your number is 415-4445-212')
    print(mo)
    mo1 = phone_number_reg.findall('My number is 415-555-4242. Your number is 415-444-5212')
    for el in mo1:
        print(el)

