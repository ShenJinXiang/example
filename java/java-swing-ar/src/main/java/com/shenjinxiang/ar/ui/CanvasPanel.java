package com.shenjinxiang.ar.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 00:05
 */
public abstract class CanvasPanel extends JPanel implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CanvasPanel.class);

    protected Graphics2D graphics2D;

    protected abstract void draw();
    protected abstract void update();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;
        draw();
    }

    @Override
    public void run() {
        while (true) {
            try {
                update();
                repaint();
                Thread.sleep(50);
            } catch (Exception e) {
                logger.error("CanvasPanel 出错", e);
            }
        }
    }
}
