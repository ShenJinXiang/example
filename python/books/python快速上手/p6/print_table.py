#!/usr/bin/env python3

import pprint


def print_table1(table):
    for row in table:
        for cell in row:
            print(cell.rjust(10), end='')
        print('')


def print_table2(table):
    rs = len(table)
    cs = len(table[0])
    for c in range(0, cs):
        for r in range(0, rs):
            print(table[r][c].rjust(10), end='  ')
        print('')


if __name__ == '__main__':
    table_data = [
        ['apples', 'oranges', 'cherries', 'banana'],
        ['Alice', 'Bob', 'Carol', 'David'],
        ['dogs', 'cats', 'moose', 'goose']
    ]

    print_table1(table_data)
    print_table2(table_data)
