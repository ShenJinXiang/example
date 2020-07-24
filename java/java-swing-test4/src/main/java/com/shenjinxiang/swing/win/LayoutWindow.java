package com.shenjinxiang.swing.win;

import javax.swing.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/10 9:56
 */
public class LayoutWindow {

    protected JFrame jFrame;

    public LayoutWindow(String title, int width, int height) {
        this.jFrame = new JFrame(title);
        this.jFrame.setBounds(20, 20, width, height);
        this.jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.jFrame.setLocationRelativeTo(null);
    }

    public void show() {
        this.jFrame.setVisible(true);
    }

    public void hide() {
        this.jFrame.setVisible(false);
    }
}
