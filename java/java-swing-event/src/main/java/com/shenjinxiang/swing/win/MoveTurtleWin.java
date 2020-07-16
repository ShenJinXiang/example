package com.shenjinxiang.swing.win;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/16 14:35
 */
public class MoveTurtleWin {

    private JFrame jFrame;
    private Turtle turtle;
    private int x = 100;
    private int y = 50;

    public MoveTurtleWin() {
        jFrame = new JFrame("TURTLE");
        turtle = new Turtle(x, y);
        jFrame.add(turtle);
        jFrame.setSize(500, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        addEvent();
    }

    private void addEvent() {
        turtle.requestFocus();
        turtle.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    y -= 10;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    x -= 10;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    x += 10;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    y += 10;
                }
                turtle.moveTo(x, y);
            }
        });
    }
}
