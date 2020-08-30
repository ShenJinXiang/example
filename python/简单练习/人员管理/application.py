#!/usr/bin/env python3
"""
人员管理练习，程序入口
"""

import click

import person_service as ps


@click.group()
def run():
    pass


@click.command("add", help='添加人员信息')
@click.option("--name", "-n", type=(str), required=True, prompt="请输入姓名", help="姓名")
@click.option("--age", "-a", type=(int), required=False, prompt="请输入年龄", help="年龄")
@click.option("--height", "-h", type=(float), required=False, prompt="请输入身高", help="身高")
@click.option("--desc", "-d", type=(str), required=False, default="暂无", help="说明介绍")
def add(name, age, height, desc):
    ps.add_person(name, age, height, desc)


@click.command("upd", help='修改人员信息')
@click.option("--id", "-n", type=(str), required=True, prompt="请输入人员id", help="人员id")
@click.option("--name", "-n", type=(str), required=False, prompt="请输入姓名", help="姓名")
@click.option("--age", "-a", type=(int), required=False, prompt="请输入年龄", help="年龄")
@click.option("--height", "-h", type=(float), required=False, prompt="请输入身高", help="身高")
@click.option("--desc", "-d", type=(str), required=False, default="暂无", help="说明介绍")
def upd(id, name, age, height, desc):
    ps.upd_person(id, name, age, height, desc)


@click.command('query', help='根据人员id查询人员信息')
@click.option("--id", "-n", type=(str), required=True, prompt="请输入人员id", help="人员id")
def query_one(id):
    ps.query_person(id)


@click.command('del', help='根据人员id删除人员信息')
@click.option("--id", "-n", type=(str), required=True, prompt="请输入人员id", help="人员id")
def del_one(id):
    ps.del_person(id)


@click.command('ls', help='查看所有人员列表')
def list():
    ps.query_all()


@click.command('ll', help='查看所有人员详细信息列表')
def list_all():
    ps.query_all_info()


run.add_command(add)
run.add_command(upd)
run.add_command(query_one)
run.add_command(del_one)
run.add_command(list)
run.add_command(list_all)

if __name__ == '__main__':
    run()
