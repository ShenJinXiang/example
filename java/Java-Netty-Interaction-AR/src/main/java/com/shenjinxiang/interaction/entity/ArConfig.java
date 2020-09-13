package com.shenjinxiang.interaction.entity;

import javafx.scene.shape.Arc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArConfig {

    private static final Logger logger = LoggerFactory.getLogger(ArConfig.class);

    private int maxTcpDataLength;
    private int serverPort;
    private int sendInterval;

    public void log() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("配置信息：")
                .append("\n\tmaxTcpDataLength: ").append(maxTcpDataLength)
                .append("\n\tserverPort: ").append(serverPort)
                .append("\n\tsendInterval: ").append(sendInterval);
        stringBuffer.append("\n");
        logger.info(stringBuffer.toString());
    }

    public int getMaxTcpDataLength() {
        return maxTcpDataLength;
    }

    public void setMaxTcpDataLength(int maxTcpDataLength) {
        this.maxTcpDataLength = maxTcpDataLength;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(int sendInterval) {
        this.sendInterval = sendInterval;
    }
}
