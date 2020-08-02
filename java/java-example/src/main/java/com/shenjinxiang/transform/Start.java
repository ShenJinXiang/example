package com.shenjinxiang.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/2 10:59
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) throws IOException {
        logger.info("start...");
//        new FileOutTest().run();
        new FileInTest().run();
        logger.info("end...");
    }
}
