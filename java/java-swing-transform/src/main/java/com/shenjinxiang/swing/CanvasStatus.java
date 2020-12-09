package com.shenjinxiang.swing;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/10/16 22:30
 */
public class CanvasStatus {

    private Color color;
    private Stroke stroke;
    private Font font;
    private AffineTransform affineTransform;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public AffineTransform getAffineTransform() {
        return affineTransform;
    }

    public void setAffineTransform(AffineTransform affineTransform) {
        this.affineTransform = affineTransform;
    }
}
