package com.shenjinxiang.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/15 10:46
 */
public class ButtonLabelWin {

    private JFrame jFrame;
    private JPanel headerPanel;
    private JLabel headerLabel;
    private JPanel controlPanel;
    private JPanel statusPanel;
    private JLabel statusLabel;
    private Font font;

    public ButtonLabelWin() {
        jFrame = new JFrame("按钮与标签");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        jFrame.setLayout(new GridLayout(3, 1));

        headerPanel = new JPanel();
        initHeaderPanel();
        controlPanel = new JPanel();
        initControlPanel();
        statusPanel = new JPanel();
        initStatusPanel();

        jFrame.add(headerPanel);
        jFrame.add(controlPanel);
        jFrame.add(statusPanel);
        jFrame.setSize(400, 200);
        jFrame.setVisible(true);
    }

    private void initHeaderPanel() {
        font = new Font("楷体", Font.PLAIN, 30);
        headerLabel = new JLabel();
        headerLabel.setBackground(Color.RED);
        headerLabel.setText("按钮点击动作监控");
        headerLabel.setFont(font);
        headerPanel.add(headerLabel);
    }

    private void initStatusPanel() {
        font = new Font("楷体", Font.PLAIN, 20);
        statusLabel = new JLabel();
        statusLabel.setFont(font);
        statusPanel.add(statusLabel);
        statusPanel.setAlignmentY(0);
    }

    private void initControlPanel() {
        controlPanel.setLayout(new FlowLayout());
        JButton okButton = new JButton("确定");
        JButton submitButton = new JButton("提交");
        JButton cancelButton = new JButton("取消");

        controlPanel.add(okButton);
        controlPanel.add(submitButton);
        controlPanel.add(cancelButton);

        okButton.addActionListener((e) -> {
            statusLabel.setText("确定按钮被点击!");
        });
        submitButton.addActionListener((e) -> {
            statusLabel.setText("提交按钮被点击!");
        });
        cancelButton.addActionListener((e) -> {
            statusLabel.setText("取消按钮被点击!");
        });

    }
}
