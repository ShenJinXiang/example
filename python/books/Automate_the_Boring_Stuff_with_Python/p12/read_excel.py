#!/usr/bin/env python3

import openpyxl as xl

wb = xl.load_workbook("./example.xlsx")
print(wb)
print(type(wb))
print(wb.sheetnames)

# sheet_names = wb.get_sheet_names
sheet1 = wb['Sheet1']
print(f"sheet title: {sheet1}")
print(sheet1.title)
print(sheet1['A1'].value)

for r in sheet1:
    for c in r:
        print(f'type: {type(c.value)}, value: {c.value}')