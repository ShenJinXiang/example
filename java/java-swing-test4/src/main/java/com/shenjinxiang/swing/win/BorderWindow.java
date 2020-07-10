package com.shenjinxiang.swing.win;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/10 9:50
 */
public class BorderWindow extends LayoutWindow {

    public BorderWindow(String title, int width, int height) {
        super(title, width, height);
        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(new JButton("东"), BorderLayout.EAST);
        this.jFrame.add(new JButton("西"), BorderLayout.WEST);
        this.jFrame.add(new JButton("南"), BorderLayout.SOUTH);
        this.jFrame.add(new JButton("北"), BorderLayout.NORTH);
        this.jFrame.add(new JButton("中"), BorderLayout.CENTER);
//        this.jFrame.pack();
    }

}
