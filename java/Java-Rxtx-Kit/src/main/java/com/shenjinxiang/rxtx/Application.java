package com.shenjinxiang.rxtx;

import com.shenjinxiang.rxtx.io.CommandReader;
import com.shenjinxiang.rxtx.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Java-Rxtx-Kit Start ...");
//        ThreadPool.getThread().execute(new RxtxClient("COM3"));
        ThreadPool.getThread().execute(new CommandReader());
    }
}
