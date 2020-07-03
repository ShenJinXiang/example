#!/usr/bin/env python3
from time import time, localtime, sleep

class Clock():

    @classmethod
    def now(cls):
        ctime = localtime(time())
        return cls(ctime.tm_hour, ctime.tm_min, ctime.tm_sec)

    def __init__(self, hour=0, minute=0, second=0):
        self._hour = hour
        self._minute = minute
        self._second = second

    def run (self):
        self._second += 1
        if (self._second >= 60) :
            self._second = 0
            self._minute += 1
            if self._minute >= 60 :
                self._minute = 0
                self._hour += 1
                if self._hour >= 24:
                    self._hour = 0

    def show(self):
        #return str(self._hour).rjust(2, '0') + str(self._minute).rjust(2, '0') + str(self._second).rjust(2, '0') 
        return '%02d:%02d:%02d' % (self._hour, self._minute, self._second)


def main():
    cl1 = Clock.now()
    while True:
        print(cl1.show())
        sleep(1)
        cl1.run()
        

if __name__ == '__main__':
    main()

