package com.shenjinxiang.netty.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sender implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);
    private boolean flag = false;

    @Override
    public void run() {
        while (true) {
            try {
//                if (flag) {
                    send();
//                }
                Thread.sleep(7000);
            } catch (Exception e) {
                logger.error("错误", e);
            }
        }
    }

    public void send() {
        if (null != Config.MULTICAST_HANDLER && Config.MULTICAST_HANDLER.isConn()) {
            Config.MULTICAST_HANDLER.sendMsg("shenjinxiang".getBytes());
        }
    }

    public void start() {
        this.flag = true;
    }

    public void end() {
        this.flag = false;
    }
}
