package com.shenjinxiang.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 17:45
 */
public class Start {

    private static  final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) throws Exception {
        logger.info("start...");
        new NettyServer().run();
        logger.info("end...");
    }
}
