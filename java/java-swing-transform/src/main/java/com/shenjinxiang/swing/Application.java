package com.shenjinxiang.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/12/3 22:47
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        logger.info("java-swing-transform start...");
        new MainWindow(500, 400);
    }
}
