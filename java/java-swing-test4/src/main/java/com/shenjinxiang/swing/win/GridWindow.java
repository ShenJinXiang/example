package com.shenjinxiang.swing.win;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/10 9:50
 */
public class GridWindow extends LayoutWindow {

    public GridWindow(String title, int width, int height) {
        super(title, width, height);
        JPanel jPanel = new JPanel();
        jPanel.add(new TextField(40));
        jFrame.add(jPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 4, 3, 3));
        String[] names = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };
        for (String name: names) {
            gridPanel.add(new JButton(name));
        }

        jFrame.add(gridPanel, BorderLayout.CENTER);
        jFrame.pack();
    }

}
