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

    private static InetSocketAddress GROUP_ADDRESS = new InetSocketAddress(Config.MULTICAST_HOST, Config.MULTICAST_PORT);
    private static final UdpHandler HANDLER = new UdpMulticastHandler(GROUP_ADDRESS);

    public static void runUdpServer() {
        if (!HANDLER.isConn()) {
            ThreadPool.getThread().execute(new UdpMulticastServer(GROUP_ADDRESS, Config.NETWOKINTERFACE, HANDLER));
        } else {
            logger.info("不能加入组播！");
        }
    }

    public static void close() {
        HANDLER.close();
    }

    public static void send(byte[] bytes) {
        HANDLER.sendMsg(bytes, GROUP_ADDRESS);
    }
}
