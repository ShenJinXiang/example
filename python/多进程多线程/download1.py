#!/usr/bin/env python3

from random import randint
from time import time, sleep


def download_task(file_name):
    print('开始下载%s.' % file_name)
    download_time = randint(5, 10)
    sleep(download_time)
    print('%s下载完成，消耗%d秒。' % (file_name, download_time))


if __name__ == '__main__' :
    start = time()
    file_name1 = 'java入门'
    file_name2 = 'python入门'
    download_task(file_name1)
    download_task(file_name2)
    end = time()
    print('下载完成，总消耗%.2f秒.' % (end - start))
