package com.shenjinxiang.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketException;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/30 16:08
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) throws SocketException {
        logger.info("start...");
        new UdpServer(5005).run();
        logger.info("end...");
    }
}
