package com.shenjinxiang.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/30 17:28
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {
        logger.info("start...");
//        new TcpClient("127.0.0.1", 5001).run();
        new UdpServer().run(5005);
        logger.info("end...");
    }
}
