#!/usr/bin/env python3
def write_lines(file_name, lines):
    try:
        with open(file_name, 'w', encoding='utf-8') as fs:
            for line in lines:
                fs.write(line)
    except FileNotFoundError:
        print('无法打开指定的文件!')
    except LookupError:
        print('指定了未知的编码!')
    except UnicodeDecodeError:
        print('读取文件时解码错误!')

if __name__ == '__main__':
    lines = []
    for r in range(101):
        s = ' ' * (101 - r) + '*' * (2 * r + 1) + '\n'
        lines.append(s)

    write_lines('./sj.txt', lines)
