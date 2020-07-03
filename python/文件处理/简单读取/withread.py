#!/usr/bin/env python3

def read(file_name):
    try:
        with open(file_name, 'r', encoding='utf-8') as f:
            print(f.read())
    except FileNotFoundError:
        print('无法打开指定的文件!')
    except LookupError:
        print('指定了未知的编码!')
    except UnicodeDecodeError:
        print('读取文件时解码错误!')
    finally:
        if f:
            f.close()
def main():
    read('./read.py')

if __name__ == '__main__':
    main()
