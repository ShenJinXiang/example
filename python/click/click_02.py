#!/usr/bin/env python3
"""
click模块使用多个参数
"""

import click


@click.command()
@click.option("--name", "-n", type=(str), required=True, prompt="请输入姓名", help="姓名")
@click.option("--age", "-a", type=(int), required=True, prompt="请输入年龄", help="年龄")
@click.option("--height", "-h", type=(float), required=True, prompt="请输入身高", help="身高")
@click.option("--info", "-i", type=(str), required=False, default="暂无", help="说明介绍")
def run(name, age, height, info):
    print(f'姓名：{name}, 年龄: {age}, 身高: {height}, 介绍: {info}!')


if __name__ == '__main__':
    run()
