package com.shenjinxiang.swing.win;

import com.shenjinxiang.swing.bean.WinElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/10 9:38
 */
public class MainWindow {

    private JFrame jFrame;
    private List<WinElement> list;
    private int layoutWindowWidth = 400;
    private int layoutWindowHeight = 200;


    public MainWindow(String title, int width, int height){
        this.jFrame = new JFrame(title);
        this.jFrame.setSize(width, height);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        show();
    }

    public void show() {
        this.jFrame.setVisible(true);
    }

    public void hide() {
        this.jFrame.setVisible(false);
    }

    public void init() {
        initList();

        JPanel jPanel = new JPanel();
        for (WinElement winElement : list) {
            winElement.getjButton().addActionListener((e) -> {
                for(WinElement winElement1 : list) {
                    winElement1.hide();
                }
                winElement.show();
            });
            jPanel.add(winElement.getjButton());
        }
        jFrame.add(jPanel);
    }

    private void initList() {
        list = new ArrayList<>();
        JButton flowButton = new JButton("FlowLayout");
        LayoutWindow layoutWindow = new FlowWindow("流式布局", layoutWindowWidth, layoutWindowHeight);

        JButton borderButton = new JButton("BorderLayout");
        LayoutWindow borderWindow = new BorderWindow("边界布局", layoutWindowWidth, layoutWindowHeight);

        JButton cardButton = new JButton("CardLayout");
        LayoutWindow cardWindow = new CardWindow("CardLayout", layoutWindowWidth, layoutWindowHeight);

        JButton gridButton = new JButton("GridLayout");
        LayoutWindow gridWindow = new GridWindow("网格布局之计算器", layoutWindowWidth, layoutWindowHeight);

        JButton gridBagButton = new JButton("GridBagLayout");
        LayoutWindow gridBagWindow = new GridBagWindow("GridBagLayout", layoutWindowWidth, layoutWindowHeight);


        list.add(new WinElement("FlowLayout", flowButton, layoutWindow));
        list.add(new WinElement("BorderLayout", borderButton, borderWindow));
        list.add(new WinElement("CardLayout", cardButton, cardWindow));
        list.add(new WinElement("GridLayout", gridButton, gridWindow));
        list.add(new WinElement("GridBagLayout", gridBagButton, gridBagWindow));
    }
}
