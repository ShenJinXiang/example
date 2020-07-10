package com.shenjinxiang.swing.win;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/10 9:50
 */
public class FlowWindow extends LayoutWindow {

    public FlowWindow(String title, int width, int height) {
        super(title, width, height);
        this.jFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        for (int i = 0; i < 24; i++) {
            this.jFrame.add(new JButton("按钮" + (i + 1)));
        }
        this.jFrame.pack();
    }
}
