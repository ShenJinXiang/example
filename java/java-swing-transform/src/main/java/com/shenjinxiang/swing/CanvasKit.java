package com.shenjinxiang.swing;

import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/10/16 22:29
 */
public class CanvasKit {

    public static CanvasStatus save(Graphics2D graphics2D) {
        CanvasStatus status = new CanvasStatus();
        status.setColor(graphics2D.getColor());
        status.setStroke(graphics2D.getStroke());
        status.setFont(graphics2D.getFont());
        status.setAffineTransform(graphics2D.getTransform());
        return status;
    }

    public static void restore(Graphics2D graphics2D, CanvasStatus canvasStatus) {
        graphics2D.setColor(canvasStatus.getColor());
        graphics2D.setStroke(canvasStatus.getStroke());
        graphics2D.setFont(canvasStatus.getFont());
        graphics2D.setTransform(canvasStatus.getAffineTransform());
    }
}
