package com.shenjinxiang.netty.io;

import com.shenjinxiang.netty.core.Config;
import com.shenjinxiang.netty.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/10/17 17:34
 */
public class IOKit {

    private static final Logger logger = LoggerFactory.getLogger(IOKit.class);

    private static final UdpHandler HANDLER = new PointUdpHandler();
    private static InetSocketAddress TARGET_ADDRESS = new InetSocketAddress(Config.TARGET_HOST, Config.TARGET_PORT);

    public static void runUdpServer() {
        if (!HANDLER.isConn()) {
            ThreadPool.getThread().execute(new UdpServer(Config.LISTEN_PORT, HANDLER));
        } else {
            logger.info("不能开启UDP服务，先关闭现有服务！");
        }
    }

    public static void close() {
        HANDLER.close();
    }

    public static void send(byte[] bytes) {
        HANDLER.sendMsg(bytes, TARGET_ADDRESS);
    }
}
