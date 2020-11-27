import sys

base_path = '/Users/shenjinxiang/Downloads/area2019/'
def t1():
    file_name1 = 'area2019.sql'
    file_name2 = 'area2019_01.sql'
    with open(base_path + file_name1, 'r') as fs:
        with open(base_path + file_name2, 'w') as ws:
            ls = fs.readlines()
            for l in ls:
                if l.startswith("INSERT INTO `area2019` VALUES ('5'"):
                    continue
                l = l.replace("area2019", "area201901")
                ws.write(l)



if __name__ == '__main__':
    t1()
    print('end...')