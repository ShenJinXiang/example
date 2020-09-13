package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.io.IOKit;
import com.shenjinxiang.interaction.kit.ByteKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpDataUtil {

    private static final Logger logger = LoggerFactory.getLogger(UdpDataUtil.class);

    public static void dataService(byte[] data) {
        try {
            if (IOKit.isQtStart()) {
                String content = ByteKit.byteArrayToHexStr(data);
                logger.info("接收到数据：" + content);
                IOKit.sendQtTcpMsg(content);
            }
        } catch (Exception e) {
            logger.error("处理udp数据出错", e);
        }
    }
}
