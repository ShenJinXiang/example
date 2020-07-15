package com.shenjinxiang.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/15 11:33
 */
public class TextFieldWin {

    private JFrame jFrame;

    public TextFieldWin() {
        jFrame = new JFrame("文本域");
        init();
    }

    private void init() {
        JLabel message = new JLabel("请输入信息");
        JTextField textField = new JTextField(15);
        JPanel centerPanel = new JPanel();
        JButton enterBtn = new JButton("确认");
        enterBtn.addActionListener((e) -> {
            message.setText("输入的信息为：" + textField.getText());
        });

        jFrame.add(message, BorderLayout.NORTH);
        centerPanel.add(textField);
        centerPanel.add(enterBtn);
        jFrame.add(centerPanel, BorderLayout.CENTER);
        jFrame.setSize(300, 200);
//        jFrame.pack();
        jFrame.setVisible(true);
    }
}
