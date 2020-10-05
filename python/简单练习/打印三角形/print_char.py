#!/usr/bin/env python3


def print_chars(num, char):
    for n in range(1, num + 1):
        print(str(n) + ' ' * len(char) * (num - n + 1) + char * (2 * n - 1))


if __name__ == '__main__':
    while(input('是否打印三角图形？') != 'n'):
        num = int(input('输入行数:'))
        char = input("字符：")
        print_chars(num, char)