#!/usr/bin/env python3

def count_file(file_name):
    word_dict = {}
    rows = 0
    wordNum = 0
    with open(file_name, 'r') as fs:
        lines = fs.readlines()
        for line in lines:
            rows += 1
            for word in line.split():
                wordNum += 1
                w = word.lower()
                if w in word_dict:
                    word_dict[w] += 1
                else:
                    word_dict[w] = 1

    return rows, wordNum, word_dict


    return rows



if __name__ == '__main__':
    rows, wordNum, word_dict = count_file('./count.py')
    print(f'行数：{rows}!')
    print(f'单词数：{wordNum}!')
    print('详细统计：')
    for w in word_dict:
        print(f'单词 【{w}】出现了{word_dict.get(w, 0)}次！')
