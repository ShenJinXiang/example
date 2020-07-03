#!/usr/bin/env python3

from threading import Thread
from random import randint
from time import time, sleep


class DownloadTask(Thread):

    def __init__(self, file_name):
        super().__init__()
        self.file_name = file_name

    def run(self):
        print('开始下载%s' % self.file_name)
        download_time = randint(5, 10)
        sleep(download_time)
        print('%s下载完成，耗时%d秒' % (self.file_name, download_time))


if __name__ == '__main__':
    start = time()
    print('开始')
    t1 = DownloadTask('java')
    t1.start()
    t2 = DownloadTask('python')
    t2.start()
    t1.join()
    t2.join()
    end = time()
    print('结束，总耗时%.2f秒' % (end - start))

