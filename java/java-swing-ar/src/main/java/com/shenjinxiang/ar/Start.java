package com.shenjinxiang.ar;

import com.shenjinxiang.ar.ui.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 22:27
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {
        logger.info("start...");
        new MainWindow();
    }
}
