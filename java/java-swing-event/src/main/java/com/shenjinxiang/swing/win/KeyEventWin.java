package com.shenjinxiang.swing.win;

import javafx.beans.property.adapter.JavaBeanLongPropertyBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/16 9:36
 */
public class KeyEventWin {

    private JFrame jFrame;

    public KeyEventWin() {
        jFrame = new JFrame("KEY_EVENT");
        JLabel message = new JLabel("请安任意键", JLabel.CENTER);
        JLabel keyChar = new JLabel("", JLabel.CENTER);
        jFrame.setSize(300, 200);
        jFrame.requestFocus();
        jFrame.add(message, BorderLayout.NORTH);
        jFrame.add(keyChar, BorderLayout.CENTER);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyChar.setText(KeyEvent.getKeyText(e.getKeyCode()) + " 键按下了！");
            }
        });
        jFrame.setVisible(true);
    }
}
