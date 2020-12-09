package com.shenjinxiang.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/12/3 22:55
 */
public class CanvasPanel extends JPanel {

    private int width;
    private int height;

    private Graphics2D graphics2D;

    public CanvasPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setSize(this.width, this.height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;
        UIKit.init(graphics2D);

        CanvasStatus canvasStatus = CanvasKit.save(graphics2D);
        graphics2D.setColor(Color.DARK_GRAY);
        graphics2D.fillRect(0, 0, width, height);

        graphics2D.setColor(Color.CYAN);
        graphics2D.drawRect(10, 10, width - 20, height - 20);

        graphics2D.drawLine(0, height / 2, width, height / 2);
        graphics2D.drawLine(width / 2, 0, width / 2, height);

        graphics2D.translate(width / 2, height / 2);

        graphics2D.setColor(Color.RED);
        graphics2D.drawLine(0, 0, width / 2, height / 2);

        graphics2D.drawLine(0, 0, 100, 0);
        graphics2D.rotate(Math.PI / 2);
        graphics2D.drawLine(0, 0, 100, 0);

        CanvasKit.restore(graphics2D, canvasStatus);

        graphics2D.setColor(Color.GREEN);
        graphics2D.drawLine(10, 10, 100, 0);

        graphics2D.drawArc(0, 0, 100, 100, 0, 120);

    }

}
