#!/usr/bin/env python3

def print_two(*args):
    arg1, arg2 = args
    print(f"args1: {arg1}, arg2: {arg2}")

def print_two_again(arg1, arg2):
    print(f"args1: {arg1}, arg2: {arg2}")


def print_one(arg):
    print(f"arg: {arg}")

def print_none():
    print("I got nothin'.")

print_two('Zen', 'Shaw')
print_two_again('Zen', 'Shaw')
print_one('First!')
print_none()
