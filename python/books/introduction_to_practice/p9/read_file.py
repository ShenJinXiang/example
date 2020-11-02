#!/usr/bin/env python3

def read_file(file_name):
    with open(file_name, 'r') as fs:
        return fs.read()

def read_file_lines(file_name):
    with open(file_name, 'r') as fs:
        lines = fs.readlines()
        return lines

if __name__ == '__main__':
    content = read_file('./01.data')
    print(content)
    print()
    print('-' * 20)

    lines = read_file_lines('./01.data')
    num = 1;
    for line in lines:
        print(f'{num}: {line}', end = '')
        num += 1
    print()

