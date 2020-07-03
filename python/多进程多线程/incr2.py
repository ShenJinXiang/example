#!/usr/bin/env python3

from threading import Thread
from os import getpid
from random import randint
from time import time, sleep

count = 0


def incr(name):
    global count
    print('%s 开始，进程号: %d' % (name, getpid()))
    while count <= 20:
        count += 1
        print(name + ': ' + str(count))
        sleep(1)
    print('%s 结束，进程号: %d count: %d ' % (name, getpid(), count))


if __name__ == '__main__':
    start = time()
    p1 = Thread(target=incr, args=('ping',))
    p1.start()
    p2 = Thread(target=incr, args=('pong',))
    p2.start()
    p1.join()
    p2.join()
    end = time()
    print('总消耗%.2f秒' % (end - start) )
