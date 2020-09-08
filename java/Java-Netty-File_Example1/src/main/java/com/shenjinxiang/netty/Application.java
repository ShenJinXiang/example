package com.shenjinxiang.netty;

import com.shenjinxiang.netty.demo.Read;
import com.shenjinxiang.netty.demo.Write;
import com.shenjinxiang.netty.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("start");
        ThreadPool.getThread().execute(new Write());
        ThreadPool.getThread().execute(new Read());
    }
}
