package com.shenjinxiang.netty.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Write implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Write.class);
    private int count;

    @Override
    public void run() {
        while (true) {
            try {
                this.count++;
                Config.LINES.put("line" + this.count);
                logger.info("放入数据，当前内容：" + Config.LINES);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
