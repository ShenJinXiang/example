package com.shenjinxiang.client.core;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 16:09
 */
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    public static final String ENCODE = "UTF-8";
    public static int PORT = 5001;
    public static String SERVER_IP = "localhost";
    public static int SERVER_PORT = 5000;

    public static void logInfo() {
        StringBuilder stringBuilder = new StringBuilder("配置信息: ");
        stringBuilder.append("\n\t 监听端口(port): ").append(Config.PORT)
                .append("\n\t 目标IP(server-host): ").append(Config.SERVER_IP)
                .append("\n\t 目标端口(server-port): ").append(Config.SERVER_PORT)
                .append("\n");
        logger.info(stringBuilder.toString());
    }

}
