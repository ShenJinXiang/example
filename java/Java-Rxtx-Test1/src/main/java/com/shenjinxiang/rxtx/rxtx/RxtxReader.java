package com.shenjinxiang.rxtx.rxtx;

import com.shenjinxiang.rxtx.kit.ByteKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RxtxReader {

    private static final Logger logger = LoggerFactory.getLogger(RxtxReader.class);

    public static void read(byte[] message) {
        String str = ByteKit.byteArrayToHexStr(message);
        logger.info("接收到数据：" + str);
        String data = str.substring(0, 16) + "EFFFF77F00000000EE5501";
        RxtxKit.sendData(data);
    }

    public static void main(String[] args) {
        String str = "AA0218AD01000000EFFFF77F00000000EE5501";
    }
}
