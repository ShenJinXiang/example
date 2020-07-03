#!/usr/bin/env python3
"""
打印 9 * 9 乘法口诀表

Author: 申锦祥
"""

for i in range(1, 10):
    for j in range(1, i + 1):
        print('%d * %d = %d' % (i, j, (i * j)), end='\t')
    print()
print('end...')
