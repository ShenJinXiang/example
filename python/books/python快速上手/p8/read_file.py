#!/usr/bin/env python3

import os

def read_file(file_path):
    f = open(file_path, 'r')
    return f.read()

def read_lines(file_path):
    f = open(file_path, 'r')
    lines = f.readlines()
    return lines


if __name__ == '__main__':
    # content = read_file(os.path.join(os.getcwd(), 'os_demo.py'))
    # print(content)

    lines = read_lines(os.path.join(os.getcwd(), 'os_demo.py'))
    for l in lines:
        print(l, end = '')