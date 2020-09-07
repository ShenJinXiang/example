package com.shenjinxiang.netty.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class Sender implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);
    private boolean flag = false;

    @Override
    public void run() {
        while (true) {
            try {
                if (flag) {
                    send();
                }
                Thread.sleep(5000);
            } catch (Exception e) {
                logger.error("错误", e);
            }
        }
    }

    public void send() {
        if (Config.UDP_HANDLER.isConn() && Config.INIT_PORTS) {
            for(InetSocketAddress address: Config.ADDRESS_LIST) {
                Config.UDP_HANDLER.sendMsg("shenjinxiang".getBytes(), address);
            }
        }
    }

    public void start() {
        this.flag = true;
    }

    public void end() {
        this.flag = false;
    }
}
