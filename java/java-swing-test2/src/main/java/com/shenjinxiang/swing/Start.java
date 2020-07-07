package com.shenjinxiang.swing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/7 14:49
 */
public class Start {

    public static void main(String[] args) {
        Object[][] unit = {
                {"张三", "86", "94", "180"},
                {"李四", "92", "96", "188"},
                {"王五", "66", "80", "146"},
                {"赵六", "98", "94", "192"},
                {"田七", "81", "83", "164"},
        };
        String[] name = {"姓名", "语文", "数学", "总成绩"};
        JTable jtable = new JTable(unit, name);

        jtable.setRowHeight(30);
        jtable.setSelectionBackground(Color.LIGHT_GRAY);
        jtable.setSelectionForeground(Color.red);
        JFrame jFrame = new JFrame("表格数据处理");
        jFrame.add(new JScrollPane(jtable));
        jFrame.setSize(350, 200);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
