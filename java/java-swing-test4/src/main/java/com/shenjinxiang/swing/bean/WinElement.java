package com.shenjinxiang.swing.bean;

import com.shenjinxiang.swing.win.LayoutWindow;

import javax.swing.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/10 10:02
 */
public class WinElement {

    private String title;
    private JButton jButton;
    private LayoutWindow layoutWindow;

    public WinElement(String title, JButton jButton, LayoutWindow layoutWindow) {
        this.title = title;
        this.jButton = jButton;
        this.layoutWindow = layoutWindow;
    }

    public void show() {
        this.layoutWindow.show();
    }

    public void hide() {
        this.layoutWindow.hide();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JButton getjButton() {
        return jButton;
    }

    public void setjButton(JButton jButton) {
        this.jButton = jButton;
    }

    public LayoutWindow getLayoutWindow() {
        return layoutWindow;
    }

    public void setLayoutWindow(LayoutWindow layoutWindow) {
        this.layoutWindow = layoutWindow;
    }

}
