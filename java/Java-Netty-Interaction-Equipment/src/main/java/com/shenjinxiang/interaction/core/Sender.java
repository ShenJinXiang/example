package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.entity.data.Ddsj;
import com.shenjinxiang.interaction.entity.data.Lljs;
import com.shenjinxiang.interaction.entity.data.Pdxp;
import com.shenjinxiang.interaction.entity.data.T0;
import com.shenjinxiang.interaction.io.IOKit;
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
                send();
                Thread.sleep(Config.EQUIPMENT_CONFIG.getSendInterval());
            } catch (Exception e) {
                logger.error("错误", e);
            }
        }
    }

    public void send() {
        if (IOKit.isMulticastUdpConn()) {
            T0 t0 = new T0(new Pdxp(this.count), 2);
            Lljs lljs = new Lljs(new Pdxp(this.count), this.count);
            Ddsj ddsj = new Ddsj(new Pdxp(this.count), this.count, 10.12f, 11.32f, 12.34f, 11.12f, 12.123f, 13.45646f);
            switch (Config.EQUIPMENT_CONFIG.getSblx()) {
                case LXB:
                    IOKit.sendMulticastMsg(t0.bytes());
                    break;
                case DMC:
                    IOKit.sendMulticastMsg(lljs.bytes());
                    break;
                case YC:
                    IOKit.sendMulticastMsg(ddsj.bytes());
                    break;
                default:
                    IOKit.sendMulticastMsg("shenjinxiang".getBytes());  // ^_^
            }
            this.count++;
        }
    }

}
