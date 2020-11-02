package com.shenjinxiang.netty;

import com.shenjinxiang.netty.core.Config;
import com.shenjinxiang.netty.io.CommandReader;
import com.shenjinxiang.netty.io.IOKit;
import com.shenjinxiang.netty.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/10/17 17:15
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        logger.info("Netty-Udp-Test Start...");
        Config.loadConfig();
        IOKit.runUdpServer();
        Thread.sleep(2000);
        ThreadPool.getThread().execute(new CommandReader());
    }

}
