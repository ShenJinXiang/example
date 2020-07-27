package com.shenjinxiang.ar.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 22:51
 */
public class QcCanvasPanel extends CanvasPanel {

    private static final Logger logger = LoggerFactory.getLogger(QcCanvasPanel.class);

    private int width;
    private int height;
    private double angle = 0;

    public QcCanvasPanel(int width, int height) {
        logger.info("QcCanvasPanel width:" + width + "  height:" + height);
        this.width = width;
        this.height = height;
        setSize(this.width, this.height);
    }

    @Override
    protected void draw() {
        graphics2D.setColor(new Color(40, 40, 40));
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.setColor(Color.white);
        System.out.println(angle);
        graphics2D.drawLine(width / 2, height / 2, width / 2 + (int)(cos(angle) * 100), height / 2 + (int)(sin(angle) * 100));
    }

    @Override
    protected void update() {
        angle += 0.05;
    }
}
