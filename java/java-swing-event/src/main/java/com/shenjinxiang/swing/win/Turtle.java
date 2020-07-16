package com.shenjinxiang.swing.win;

import java.awt.*;
import java.util.concurrent.CancellationException;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/16 10:39
 */
public class Turtle extends Canvas {

    private int x = 100;
    private int y = 100;

    public Turtle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x + 0, y + 40, 30, 30);
        g.fillOval(x + 0, y + 40, 30, 30);
        g.fillOval(x + 90, y + 40, 30, 30);
        g.fillOval(x + 0, y + 110, 30, 30);
        g.fillOval(x + 90, y + 110, 30, 30);
        g.fillOval(x + 50, y + 130, 20, 50);
        g.fillOval(x + 40, y + 0, 40, 70);
        g.setColor(Color.BLACK);
        g.fillOval(x + 50, y + 15, 5, 5);
        g.fillOval(x + 65, y + 15, 5, 5);
        g.setColor(Color.GREEN);
        g.fillOval(x + 10, y + 30, 100, 120);
        g.setColor(Color.BLACK);
        g.drawLine(x + 24, y + 50, x + 40, y + 67);
        g.drawLine(x + 97, y + 50, x + 80, y + 67);
        g.drawLine(x + 24, y + 130, x + 40, y + 113);
        g.drawLine(x + 97, y + 130, x + 80, y + 113);
        g.drawLine(x + 40, y + 67, x + 80, y + 67);
        g.drawLine(x + 40, y + 113, x + 80, y + 113);
        g.drawLine(x + 10, y + 90, x + 110, y + 90);
        g.drawLine(x + 60, y + 30, x + 60, y + 150);
    }
}
