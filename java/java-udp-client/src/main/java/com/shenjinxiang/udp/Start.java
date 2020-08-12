package com.shenjinxiang.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/30 16:31
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) throws IOException {
        logger.info("start...");
        new UdpClient(5006).run("127.0.0.1", 5005);
        logger.info("end...");
    }
}
