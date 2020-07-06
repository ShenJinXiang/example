package com.shenjinxiang.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/6 17:10
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {
        logger.info("start..");
        JFrame jFrame = new JFrame();

        jFrame.setBounds(100, 100, 500, 260);
        jFrame.setVisible(true);
        jFrame.setTitle("java-swing-test1");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logger.info("end");
    }
}
