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
    protected int width;
    protected int height;

    protected abstract void draw();
    protected abstract void update();

    public CanvasPanel () {

    }
    public CanvasPanel (int width, int height) {
        this.width = width;
        this.height = height;
        setSize(this.width, this.height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;
        // 抗锯齿
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        draw();
//        graphics2D.dispose();
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
