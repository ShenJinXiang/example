#!/usr/bin/env python3

import os


def join():
    path1 = os.path.join("usr", 'bin', 'spam')
    print('path1: ' + path1)

    files = ['accounts.txt', 'details.csv', 'invite.docx']
    for name in files:
        print(os.path.join(path1, name))


def cwd():
    print(os.getcwd())
    os.chdir('/Users/shenjinxiang')
    print(os.getcwd())


def mkdirs():
    path = '/Users/shenjinxiang/temp/20201005'
    print(os.getcwd())
    os.makedirs(path)
    os.chdir(path)
    print(os.getcwd())

def path():
    p1 = os.getcwd()
    print(p1)
    print(os.path.abspath("."))
    print(os.path.relpath('/User/shenjinxiang/temp/20201005', '.'))


if __name__ == '__main__':
    # join()
    # cwd()
    # mkdirs()
    path()
