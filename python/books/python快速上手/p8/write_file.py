#!/usr/bin/env python3

import os

def write_input(file_path):
    f = open(file_path, 'w')
    while True:
        line = input('请输入内容（exit 退出）：')
        if (line == 'exit'):
            break;
        f.write(line + "\n")
    f.close()

if __name__ == '__main__':
    write_input(os.path.join(os.getcwd(), 'input.txt'))

