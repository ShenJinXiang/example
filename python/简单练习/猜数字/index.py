#!/usr/bin/env python3
'''
猜数字游戏
计算机出一个1~100之间的随机数由人来猜
计算机根据人猜的数字分别给出提示大一点/小一点/猜对了

Author: 申锦祥
'''
import random

answer = random.randint(0, 100)
counter = 0

while True:
    counter += 1
    num = int(input('请输入数据：'))
    if num > answer:
        print("小一点")
    elif num < answer:
        print("大一点")
    else :
        print('恭喜，答对了')
        break

print('你一共回答了%d次' % counter)
if counter > 7:
    print('笨蛋')
    flag = False
