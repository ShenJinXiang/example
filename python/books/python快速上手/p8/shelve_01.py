#!/usr/bin/env python3

import shelve


def write(file_name):
    shelfile = shelve.open(file_name)
    cats = ['Zophie', 'Pooka', 'Simon']
    shelfile['cats'] = cats
    shelfile.close()

def read(file_name):
    shelfile = shelve.open(file_name)
    cats = shelfile['cats']
    print(cats)

if __name__ == '__main__':
    # write('cats')
    read('cats')
    print('end...')

