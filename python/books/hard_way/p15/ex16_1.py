#!/usr/bin/env python3

filename = input('文件名：')
print("输入内容，写入文件")
txt = open(filename, 'w')
while(True):
    line = input('> ')
    if (line == 'exit'):
        break
    txt.write(line)
    txt.write('\n')

txt.close()
print('end...')