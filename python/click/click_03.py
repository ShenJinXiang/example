#!/usr/bin/env python3
"""
click模块，命令行分组
"""

import click


@click.group()
def run():
    pass


@click.command("add", help="添加人员信息")
@click.option("--name", "-n", type=(str), required=True, prompt="请输入姓名", help="姓名")
@click.option("--age", "-a", type=(int), required=True, prompt="请输入年龄", help="年龄")
@click.option("--height", "-h", type=(float), required=True, prompt="请输入身高", help="身高")
@click.option("--info", "-i", type=(str), required=False, default="暂无", help="说明介绍")
def add(name, age, height, info):
    print(f'添加人员：[姓名：{name}, 年龄: {age}, 身高: {height}, 介绍: {info}]!')


@click.command("upd", help="修改人员信息")
@click.option("--name", "-n", type=(str), required=True, prompt="请输入姓名", help="姓名")
@click.option("--age", "-a", type=(int), required=False, prompt="请输入年龄", help="年龄")
@click.option("--height", "-h", type=(float), required=False, prompt="请输入身高", help="身高")
@click.option("--info", "-i", type=(str), required=False, default="暂无", help="说明介绍")
def upd(name, age, height, info):
    print(f'修改人员：[姓名：{name}, 年龄: {age}, 身高: {height}, 介绍: {info}]!')


@click.command("del", help="删除人员信息")
@click.option("--name", "-n", type=(str), required=True, prompt="请输入姓名", help="姓名")
def delete(name):
    print(f'删除人员：[姓名：{name}]!')


@click.command("list", help="查询人员信息")
def list():
    print(f'显示人员列表!')


run.add_command(add)
run.add_command(upd)
run.add_command(delete)
run.add_command(list)

if __name__ == '__main__':
    run()
