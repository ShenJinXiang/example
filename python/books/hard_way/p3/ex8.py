#!/usr/bin/env python3

fomatter = "{} {} {} {}"
print(fomatter.format(1, 2, 3, 4))
print(fomatter.format("one", "two", "three", "four"))
print(fomatter.format(True, False, False, True))
print(fomatter.format(fomatter, fomatter, fomatter, fomatter))
print(fomatter.format(
    "Try your",
    "Own text here",
    "Mybe a poem",
    "Or a song about fear"
))