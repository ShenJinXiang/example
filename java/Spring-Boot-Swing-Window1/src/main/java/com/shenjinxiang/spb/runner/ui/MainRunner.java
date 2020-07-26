package com.shenjinxiang.spb.runner.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/26 20:48
 */
@Component
public class MainRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MainRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("MainRunner start...");
        JFrame jFrame = new JFrame();
        jFrame.setTitle("");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setBounds(0, 0, 200, 180);
//        jFrame.setResizable(false);
//        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.green);
        jPanel.setSize(dimension.width, dimension.height);
        jFrame.add(jPanel);

        logger.info("screen width: " + dimension.width + "  height: " + dimension.height);

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                Dimension dimension1 = jFrame.getContentPane().getSize();
                logger.info("opened width: " + dimension1.width + "  height: " + dimension1.height);
            }
        });
        Dimension dimension0 = jFrame.getContentPane().getSize();
        logger.info("before visible width: " + dimension0.width + "  height: " + dimension0.height);
        jFrame.setVisible(true);
        Dimension dimension2 = jFrame.getContentPane().getSize();
        logger.info("after visible width: " + dimension2.width + "  height: " + dimension2.height);
    }
}
