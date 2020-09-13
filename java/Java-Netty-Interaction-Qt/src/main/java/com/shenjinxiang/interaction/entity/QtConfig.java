package com.shenjinxiang.interaction.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QtConfig {

    private static final Logger logger = LoggerFactory.getLogger(QtConfig.class);

    private String centralServerHost;
    private int centralServerPort;

    public void log() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("配置信息：")
                .append("\n\tcentralServerHost: ").append(centralServerHost)
                .append("\n\tcentralServerPort: ").append(centralServerPort);
        stringBuffer.append("\n");
        logger.info(stringBuffer.toString());
    }

    public String getCentralServerHost() {
        return centralServerHost;
    }

    public void setCentralServerHost(String centralServerHost) {
        this.centralServerHost = centralServerHost;
    }

    public int getCentralServerPort() {
        return centralServerPort;
    }

    public void setCentralServerPort(int centralServerPort) {
        this.centralServerPort = centralServerPort;
    }
}
