package com.shenjinxiang.netty.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Read implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Read.class);

    @Override
    public void run() {
        while (true) {
            try {
                String str = Config.LINES.poll();
                logger.info("读取数据，当前内容：" + Config.LINES);
                logger.info("->" + str);
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
