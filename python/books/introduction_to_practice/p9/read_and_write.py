#!/usr/bin/env python3


def copy_file(from_file, to_file):
    ff = open(from_file, 'r')
    ft = open(to_file, 'w')
    ft.write(ff.read())
    ft.close()
    ff.close()


if __name__ == '__main__':
    copy_file('01.data', '02.data')