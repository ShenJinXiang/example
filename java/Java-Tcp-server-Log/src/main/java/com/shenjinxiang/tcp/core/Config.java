package com.shenjinxiang.tcp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 16:09
 */
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    public static final String ENCODE = "UTF-8";
    public static int PORT = 8888;

    public static void logInfo() {
        StringBuilder stringBuilder = new StringBuilder("配置信息: ");
        stringBuilder.append("\n\t 监听端口(port): ").append(Config.PORT)
                .append("\n");
        logger.info(stringBuilder.toString());
    }

}
