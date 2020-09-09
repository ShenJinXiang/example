package com.shenjinxiang.netty.core;

import com.shenjinxiang.netty.entity.*;
import com.shenjinxiang.netty.kit.ByteKit;
import com.shenjinxiang.netty.kit.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class Sender implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);
    private boolean flag = false;

    private int count = 0;

    @Override
    public void run() {
        while (true) {
            try {
                if (flag) {
                    send();
                }
                Thread.sleep(Config.CENTER_CONFIG.getSendInterval());
            } catch (Exception e) {
                logger.error("错误", e);
            }
        }
    }

    public void send() {
        if (null != Config.UDP_HANDLER && Config.UDP_HANDLER.isConn()) {
            T0 t0 = new T0(new Pdxp(this.count), 2);
            Lljs lljs = new Lljs(new Pdxp(this.count), this.count);
            Ddsj ddsj = createDdsj();
            for(Target target: Config.CENTER_CONFIG.getTargets()) {
                switch (target.getSblx()) {
                    case LXB:
                        Config.UDP_HANDLER.sendMsg(t0.bytes(), target.getAddress());
                        Config.UDP_HANDLER.sendMsg(lljs.bytes(), target.getAddress());
                        Config.UDP_HANDLER.sendMsg(ddsj.bytes(), target.getAddress());
                        break;
                    case DMC:
                        Config.UDP_HANDLER.sendMsg(lljs.bytes(), target.getAddress());
                        break;
                    case YC:
                        Config.UDP_HANDLER.sendMsg(t0.bytes(), target.getAddress());
                        Config.UDP_HANDLER.sendMsg(lljs.bytes(), target.getAddress());
                        break;
                    default:
                        Config.UDP_HANDLER.sendMsg("shenjinxiang".getBytes(), target.getAddress());
                }
            }
            this.count++;
        }
    }

    private Ddsj createDdsj() {
        String[] ds = getDdLine();
        if (null != ds) {
            int c = Integer.parseInt(ds[0].trim());
            int t = Integer.parseInt(ds[1].trim());
            float x = Float.parseFloat(ds[2].trim());
            float y = Float.parseFloat(ds[3].trim());
            float z = Float.parseFloat(ds[4].trim());
            float vx = Float.parseFloat(ds[5].trim());
            float vy = Float.parseFloat(ds[6].trim());
            float vz = Float.parseFloat(ds[7].trim());
            return  new Ddsj(new Pdxp(c), t, x, y, z, vx, vy, vz);
        } else {
            return new Ddsj(new Pdxp(this.count), this.count, 10.12f, 11.32f, 12.34f, 11.12f, 12.123f, 13.45646f);
        }
    }

    private String[] getDdLine() {
        String ddStr = Config.FILE_LINE_READER.readLine();
        if (StrKit.notBlank(ddStr)) {
            if (ddStr.contains("index")) {
                ddStr = Config.FILE_LINE_READER.readLine();
            }
        }

        if (StrKit.isBlank(ddStr)) {
            return null;
        }
        return ddStr.split(",");
    }

    public void start() {
        this.flag = true;
    }

    public void end() {
        this.flag = false;
    }
}
