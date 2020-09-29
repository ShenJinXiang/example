#!/usr/bin/env python3

import click
import pyperclip

passwords = {
    'email': 'email123456',
    'blog': 'blog123',
    'site': 'site123456'
}

@click.command()
@click.option("--name", "-n", type=(str), default="", help='名称')
def service(name):
    if name in passwords :
        pyperclip.copy(passwords.get(name))
        print(f'{name}: {passwords.get(name)}')
    else:
        print('not found!')


if __name__ == '__main__':
    service()