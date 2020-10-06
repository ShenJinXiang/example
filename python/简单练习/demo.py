#!/usr/bin/env python3

from random import randint, shuffle
import sys

def d1():
    A0 = {'a': 1, 'b': 3, 'c': 2, 'd': 5, 'e': 4}
    A1 = {i: A0.get(i) * A0.get(i) for i in A0.keys()}
    print(A0)
    print(A1)

def d2():
    list1 = [i * i for i in range(4, 11)]
    print(list1)

def d3():
    for i in range(10):
        a = randint(0, 99)
        print(a)

    items = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
    for i in range(10):
        shuffle(items)
        print(items)

def d4():
    print("sys.argv: ")
    print(sys.argv)
    print('sys.path: ')
    print(sys.path)
    print('sys.version: ')
    print(sys.version)
    print('sys.platform: ')
    print(sys.platform)
    print('sys.modules: ')
    print(sys.modules)
    sys.exit(0)


if __name__ == '__main__':
    d4()
