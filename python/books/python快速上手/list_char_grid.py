#!/usr/bin/env python3
"""
字符图网格
"""

grid = [
    ['.', '.', '.', '.', '.', '.'],
    ['.', 'O', 'O', '.', '.', '.'],
    ['O', 'O', 'O', 'O', '.', '.'],
    ['O', 'O', 'O', 'O', 'O', '.'],
    ['.', 'O', 'O', 'O', 'O', 'O'],
    ['O', 'O', 'O', 'O', 'O', '.'],
    ['O', 'O', 'O', 'O', '.', '.'],
    ['.', 'O', 'O', '.', '.', '.'],
    ['.', '.', '.', '.', '.', '.']
]

if __name__ == '__main__':
    r = len(grid)
    c = len(grid[0])
    for i in range(0, c):
        for j in range(0, r):
            print(grid[j][i], end='  ')
        print()
    print("end...")
