#!/usr/bin/env python3

def collatz(number):
    if number % 2 == 0:
        return number // 2
    else:
        return number * 3 + 1


if __name__ == '__main__':
    while True:
        try:
            number = int(input('请输入一个数字：'))
        except ValueError:
            print('value error')
        else:
            break;
    count = 0
    while number != 1:
        number = collatz(number)
        print("number: " + str(number))
        count += 1

    print("count: " + str(count))
