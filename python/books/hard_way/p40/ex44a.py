#!/usr/bin/env python3

class Parent(object):

    def implicit(self):
        print("Parent implicit()")

class Child(Parent):
    pass


dad = Parent()
son = Child()

dad.implicit()
son.implicit()