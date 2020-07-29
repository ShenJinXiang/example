package com.shenjinxiang.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/29 9:58
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) throws IOException {
        logger.info("start...");
        new TcpServer(5001);
        logger.info("end...");
    }


}
