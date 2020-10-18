#!/usr/bin/evn python3

while True:
    try:
        one, two = eval(input("input numbers:"))
        result = f"{one} / {two} = {one / two}"
    except Exception as err:
        print("err:", err)
    else:
        print("result:", result)
    finally:
        print("完成！")