package com.shenjinxiang.swing;

import javax.swing.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/12/3 22:51
 */
public class MainWindow extends JFrame {

    private int width;
    private int height;


    public MainWindow(int width, int height) {
        this.width = width;
        this.height = height;
        setSize(this.width, this.height);
        setTitle("main");
        CanvasPanel panel = new CanvasPanel(this.width, this.height - 30);
        this.add(panel);
        this.setLayout(null);
        panel.setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
