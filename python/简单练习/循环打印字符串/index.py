#!/usr/bin/env python3

import os
import time

def run() :
    content = '中华人民共和国万岁，世界人民大团结万岁！'
    while True:
        # 清理屏幕上的输出
        #os.system('cls')  
        os.system('clear')
        print(content)

        # 休眠200毫秒
        time.sleep(0.5)
        content = content[1:] + content[0]

if __name__ == '__main__':
    run()
