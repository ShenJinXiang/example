import pprint

if __name__ == '__main__':
    allGuests = {'Alice': {'apples': 5, 'pretzels': 12}, 'Bob': {'ham sandwiches': 3, 'apples': 2},
                 'Carol': {'cups': 3, 'apple pies': 1}}
    str1 = pprint.pformat(allGuests)

    print(str1)
    pprint.pprint(allGuests)
    print('-' * 60)
    emp = {}
    print(emp)
