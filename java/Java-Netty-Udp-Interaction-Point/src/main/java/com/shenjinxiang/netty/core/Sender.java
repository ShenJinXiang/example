package com.shenjinxiang.netty.core;

import com.shenjinxiang.netty.entity.Ddsj;
import com.shenjinxiang.netty.entity.Lljs;
import com.shenjinxiang.netty.entity.Pdxp;
import com.shenjinxiang.netty.entity.T0;
import com.shenjinxiang.netty.kit.ByteKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sender implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);
    private boolean flag = false;
    private int count = 0;

    @Override
    public void run() {
        while (true) {
            try {
//                if (flag) {
                    send();
//                }
                Thread.sleep(Config.POINT_CONFIG.getSendInterval());
            } catch (Exception e) {
                logger.error("错误", e);
            }
        }
    }

    public void send() {
        if (null != Config.MULTICAST_HANDLER && Config.MULTICAST_HANDLER.isConn()) {
            T0 t0 = new T0(new Pdxp(this.count), 2);
            Lljs lljs = new Lljs(new Pdxp(this.count), this.count);
            Ddsj ddsj = new Ddsj(new Pdxp(this.count), this.count, 10.12f, 11.32f, 12.34f, 11.12f, 12.123f, 13.45646f);
            switch (Config.POINT_CONFIG.getSblx()) {
                case LXB:
                    Config.MULTICAST_HANDLER.sendMsg(t0.bytes());
                    break;
                case DMC:
                    Config.MULTICAST_HANDLER.sendMsg(lljs.bytes());
                    break;
                case YC:
                    Config.MULTICAST_HANDLER.sendMsg(ddsj.bytes());
                    break;
                default:
                    Config.MULTICAST_HANDLER.sendMsg("shenjinxiang".getBytes());  // ^_^
            }
            this.count++;
        }
    }

    public void start() {
        this.flag = true;
    }

    public void end() {
        this.flag = false;
    }
}
