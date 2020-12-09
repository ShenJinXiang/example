package com.shenjinxiang.swing;

import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/10/18 17:30
 */
public class UIKit {

    public static void init(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    }

    public static void drawString(Graphics2D graphics2D, String text, FontMetrics fontMetrics, int x, int y) {
        if (StrKit.isBlank(text)) {
            return;
        }
        int rectifyHeight = fontMetrics.getAscent() - (fontMetrics.getHeight() / 2);
        graphics2D.setFont(fontMetrics.getFont());
        int textWidth = fontMetrics.stringWidth(text);
        graphics2D.drawString(
                text,
                x - textWidth / 2,
                y + rectifyHeight
        );
    }

    public static void drawString(Graphics2D graphics2D, String text, Font font, int x, int y) {
        if (StrKit.isBlank(text)) {
            return;
        }
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        int rectifyHeight = fontMetrics.getAscent() - (fontMetrics.getHeight() / 2);
        graphics2D.setFont(font);
        int textWidth = fontMetrics.stringWidth(text);
        graphics2D.drawString(
                text,
                x - textWidth / 2,
                y + rectifyHeight
        );
    }

    public static void drawLeftString(Graphics2D graphics2D, String text, FontMetrics fontMetrics, int x, int y) {
        if (StrKit.isBlank(text)) {
            return;
        }
        int rectifyHeight = fontMetrics.getAscent() - (fontMetrics.getHeight() / 2);
        graphics2D.setFont(fontMetrics.getFont());
        graphics2D.drawString(
                text,
                x,
                y + rectifyHeight
        );
    }

    public static void drawLeftString(Graphics2D graphics2D, String text, Font font, int x, int y) {
        if (StrKit.isBlank(text)) {
            return;
        }
        FontMetrics fm = graphics2D.getFontMetrics(font);
        int rectifyHeight = fm.getAscent() - (fm.getHeight() / 2);
        graphics2D.setFont(font);
        graphics2D.drawString(
                text,
                x,
                y + rectifyHeight
        );
    }

    public static void drawRightString(Graphics2D graphics2D, String text, FontMetrics fontMetrics, int x, int y) {
        if (StrKit.isBlank(text)) {
            return;
        }
        int rectifyHeight = fontMetrics.getAscent() - (fontMetrics.getHeight() / 2);
        int textWidth = fontMetrics.stringWidth(text);
        graphics2D.setFont(fontMetrics.getFont());
        graphics2D.drawString(
                text,
                x - textWidth,
                y + rectifyHeight
        );
    }

    public static void drawRightString(Graphics2D graphics2D, String text, Font font, int x, int y) {
        if (StrKit.isBlank(text)) {
            return;
        }
        FontMetrics fm = graphics2D.getFontMetrics(font);
        int rectifyHeight = fm.getAscent() - (fm.getHeight() / 2);
        int textWidth = fm.stringWidth(text);
        graphics2D.setFont(font);
        graphics2D.drawString(
                text,
                x - textWidth,
                y + rectifyHeight
        );
    }

    public static int textWidth(Graphics2D graphics2D, String text, Font font) {
        FontMetrics fm = graphics2D.getFontMetrics(font);
        int textWidth = fm.stringWidth(text);
        return textWidth;
    }

    public static void drawStatusBtn(Graphics2D graphics2D, int sx, int sy, int size, boolean checked, Color primaryColor, Color defaultColor) {
        CanvasStatus canvasStatus = CanvasKit.save(graphics2D);
        if (checked) {
            graphics2D.setColor(primaryColor);
            graphics2D.fillArc(sx, sy, size, size, 0, 360);
        } else {
            graphics2D.setColor(defaultColor);
            graphics2D.drawArc(sx, sy, size, size, 0, 360);
        }
        CanvasKit.restore(graphics2D, canvasStatus);
    }

}
