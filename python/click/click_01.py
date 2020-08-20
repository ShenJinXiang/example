#!/usr/bin/env python3
"""
click 模块简单使用
练习1
"""

import click


@click.command()
@click.option("--name", "-n", type=(str), default="world", help='名称')
def run(name):
    click.echo(name)
    print(f'hello {name}!')


if __name__ == '__main__':
    run()
