#!/usr/bin/env python3

import tkinter as tk

win = tk.Tk();
win.geometry("450x300")
win.title("pack")

btn1 = tk.Button(win, width = 25, text = "按钮1")
btn1.pack()
btn2 = tk.Button(win, width = 25, text = "按钮2")
btn2.pack()
btn3 = tk.Button(win, width = 25, text = "按钮3")
btn3.pack()
win.mainloop()
