package com.shenjinxiang.swing.win;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/16 10:01
 */
public class MouseEventWin {

    private JFrame jFrame;
    private JLabel actionLabel;
    private JLabel location;

    public MouseEventWin() {
        actionLabel = new JLabel("当前鼠标操作: ");
        location = new JLabel("当前鼠标位置: ");

        jFrame = new JFrame("鼠标事件");
        jFrame.setBounds(100, 100, 300, 200);
        jFrame.add(actionLabel, BorderLayout.CENTER);
        jFrame.add(location, BorderLayout.NORTH);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        addEvent();
    }

    private void addEvent() {
        actionLabel.requestFocus();
        actionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionLabel.setText("当前鼠标操作: 单击按键");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                actionLabel.setText("当前鼠标操作: 按下按键");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                actionLabel.setText("当前鼠标操作: 按键释放");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                actionLabel.setText("当前鼠标操作: 进入标签");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                actionLabel.setText("当前鼠标操作: 移除标签");
            }
        });

        actionLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                location.setText("当前鼠标位置: （" + e.getX() + ", " + e.getY() + "）");
            }
        });
    }
}
