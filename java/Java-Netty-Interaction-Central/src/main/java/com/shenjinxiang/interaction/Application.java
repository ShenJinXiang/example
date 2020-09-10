package com.shenjinxiang.interaction;

import com.shenjinxiang.interaction.io.NettyTcpServer;
import com.shenjinxiang.interaction.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Application
 * @Author ShenjinXiang
 * @Date 2020/9/10 0010 22:36
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Central Start...");
        ThreadPool.getThread().execute(new NettyTcpServer(9999));
    }
}
