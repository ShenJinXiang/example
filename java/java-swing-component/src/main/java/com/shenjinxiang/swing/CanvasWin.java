package com.shenjinxiang.swing;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/15 11:47
 */
public class CanvasWin {

    private JFrame jFrame;

    public CanvasWin() {
        jFrame = new JFrame("CANVS");
        jFrame.add(new MyCanvas());
        jFrame.setSize(260, 250);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class MyCanvas extends Canvas {
        @Override
        public void paint(Graphics graphics) {
            Graphics2D g = (Graphics2D)graphics;
            g.setColor(Color.BLUE);
            g.drawOval(10, 10, 80, 80);
            g.setColor(Color.BLACK);
            g.drawOval(80, 10, 80, 80);
            g.setColor(Color.RED);
            g.drawOval(150, 10, 80, 80);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("楷体", Font.BOLD, 20));
            g.drawString("好好学习，天天向上", 45, 200);
            g.setColor(Color.BLACK);
            g.drawArc(10, 10, 100, 100, 0, 270);
        }
    }
}
