package com.shenjinxiang.swing;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/8 8:41
 */
public class Start {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("java-swing-test3");
        JPanel jPanel = new JPanel();
        jPanel.add(new JTextField(20));
        jPanel.add(new JButton("CLICK"));
        jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(50, 50, 400, 200);
        jFrame.setVisible(true);

    }
}
