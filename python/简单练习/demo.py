#!/usr/bin/env python3

from random import *
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
    n = 10
    print('random(): ', end=' ')
    for i in range(n):
        print(random(), end='    ')
    print()

    print('uniform(10, 20): ', end='  ')
    for i in range(n):
        print(uniform(10, 20), end='    ')
    print()

    print('randint(10, 20): ', end='  ')
    for i in range(n):
        print(randint(10, 20), end='    ')
    print()

    print('randrange(3, 20, 2): ', end='  ')
    for i in range(n):
        print(randrange(3, 20, 2), end='    ')
    print()

    print('choice(["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"]): ', end='  ')
    for i in range(n):
        print(choice(['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N']), end='    ')
    print()

    print('shuffle(["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"]): ', end='  ')
    for i in range(n):
        l = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N']
        shuffle(l)
        print(l, end = '    ')
    print()

    print('sample("ABCDEFGHIJKLMN", 2): ', end='  ')
    for i in range(n):
        print(sample("ABCDEFGHIJKLMN", 2), end='    ')
    print()


def d5():
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
