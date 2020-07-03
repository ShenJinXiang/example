#!/usr/bin/env python3

from math import sqrt 

class Triangle():

    def __init__(self, a, b, c):
        self._a = a
        self._b = b
        self._c = c

    @staticmethod
    def valid(a, b, c):
        return a + b > c and b + c > a and a + c > b

    def perimeter(self):
        return self._a + self._b + self._c

    def area(self):
        half = self.perimeter() / 2
        return sqrt(half * (half - self._a) * (half - self._b) * (half - self._c))

def main(datas):
    for d in datas:
        print(d)
        if Triangle.valid(d[0], d[1], d[2]):
            print(d, 'ok')
            t = Triangle(d[0], d[1], d[2])
            print(t.perimeter())
            print(t.area())
        else:
            print(d, 'error')

if __name__ == '__main__':
    datas = [
            (1, 2, 3),
            (3, 4, 5),
            (2, 3, 4)
            ]
    main(datas)
