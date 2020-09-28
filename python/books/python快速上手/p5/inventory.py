#!/usr/bin/env python3


def displayInventory(inventory):
    print('Inventory: ')
    count = 0;
    sum = 0;
    for key, val in inventory.items():
        print(str(val), ' ', key)
        count += 1
        sum += val
    print(f"共有{count}种物品，{sum}件")
    print(list(inventory.keys()))
    print(list(inventory.values()))


if __name__ == '__main__':
    stuff = {'rope': 1, 'torch': 6, 'gold coin': 42, 'dagger': 1, 'arrow': 12}
    displayInventory(stuff)