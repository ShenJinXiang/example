package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.entity.RunStatus;
import com.shenjinxiang.interaction.io.IOKit;
import com.shenjinxiang.interaction.kit.ByteArrayConveter;
import com.shenjinxiang.interaction.kit.ByteKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sender implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    private int count = 20;
    private boolean flag = false;

    @Override
    public void run() {
        while (true) {
            try {
                if (flag) {
                    sendMsg();
                }
                Thread.sleep(Config.AR_CONFIG.getSendInterval());
            } catch (Exception e) {
                logger.error("发生错误", e);
            }
        }
    }

    private void sendMsg() {
        if (IOKit.isTcpConn()) {
            String head = ByteKit.byteArrayToHexStr(ByteArrayConveter.getByteArray(this.count));
            String str = "00000014000101380103020403010402000300000000000000000000000000000000";
            IOKit.sendTcpMsg(Config.WAVE_DATA_PREFIX + str);
//            String msg = head +  "000001380103020403010402000300000000000000000000000000000000";
//            IOKit.sendTcpMsg(Config.WAVE_DATA_PREFIX + msg);
            this.count++;
        }
    }

    public void begin() {
        this.count = 1;
        this.flag = true;
    }

    public void end() {
        this.flag = false;
    }

    public static void main(String[] args) {
        String head = ByteKit.byteArrayToHexStr(ByteArrayConveter.getByteArray(1));
        String str = "00000014000101380103020403010402000300000000000000000000000000000000";
        String msg = head +  "000001380103020403010402000300000000000000000000000000000000";
        System.out.println(str);
        System.out.println(msg);
    }

}
