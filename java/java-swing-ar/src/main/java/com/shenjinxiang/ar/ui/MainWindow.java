package com.shenjinxiang.ar.ui;

import com.shenjinxiang.ar.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 22:29
 */
public class MainWindow extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(MainWindow.class);

    private int screenWidth;
    private int screenHeight;
    private int width;
    private int height;

    public MainWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = dimension.width;
        this.screenHeight = dimension.height;

        setBounds(0, 0, this.screenWidth, this.screenHeight);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                initSize();
                initComponents();
            }
        });
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initComponents() {
        CanvasPanel qcCanvasPanel = new QcCanvasPanel(width, height / 3);
        add(qcCanvasPanel);

        ThreadPool.getThread().execute(qcCanvasPanel);
    }

    private void initSize() {
        Dimension dimension = getContentPane().getSize();
        this.width = dimension.width;
        this.height = dimension.height;
        logger.info("width: " + width + " height: " + height);
    }
}
