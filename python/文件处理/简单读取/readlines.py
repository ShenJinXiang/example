#!/usr/bin/env python3
import time

def readlines(file_name):
    try :
        with open(file_name, 'r', encoding='utf-8') as f:
            lines = f.readlines()
            return lines
    except FileNotFoundError:
        print('无法打开指定的文件!')
    except LookupError:
        print('指定了未知的编码!')
    except UnicodeDecodeError:
        print('读取文件时解码错误!')

def printlines(file_name):
    try:
        with open(file_name, 'r', encoding='utf-8') as f:
            for line in f:
                print(line, end='')
                time.sleep(1)
            print()
    except FileNotFoundError:
        print('无法打开指定的文件!')
    except LookupError:
        print('指定了未知的编码!')
    except UnicodeDecodeError:
        print('读取文件时解码错误!')

if __name__ == '__main__':
    """
    lines = readlines('./withread.py')
    for line in lines:
        print(line, end='')
    """
    printlines('./withread.py')
