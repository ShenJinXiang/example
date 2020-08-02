package com.shenjinxiang.netty;

import com.shenjinxiang.netty.kit.NettyTcpClient;
import com.shenjinxiang.netty.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/1 20:44
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {
        logger.info("start...");
//        NettyTcpServer server = new NettyTcpServer(5001);
//        ThreadPool.getThread().execute(server);
        NettyTcpClient client = new NettyTcpClient("127.0.0.1", 5001);
        ThreadPool.getThread().execute(client);
        logger.info("end...");
    }
}
