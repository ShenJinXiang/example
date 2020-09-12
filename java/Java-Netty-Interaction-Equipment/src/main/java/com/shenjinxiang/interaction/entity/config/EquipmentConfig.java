package com.shenjinxiang.interaction.entity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquipmentConfig {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentConfig.class);

    private int udpServerListenPort;
    private String udpMulticastNetwokinterface;
    private String udpMulticastHost;
    private int udpMulticastPort;
    private Sblx sblx;
    private int sendInterval;

    public void log() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("配置信息：")
                .append("\n\tudpServerListenPort: ").append(udpServerListenPort)
                .append("\n\tudpMulticastNetwokinterface: ").append(udpMulticastNetwokinterface)
                .append("\n\tudpMulticastHost: ").append(udpMulticastHost)
                .append("\n\tudpMulticastPort: ").append(udpMulticastPort)
                .append("\n\tsblx: ").append(sblx)
                .append("\n\tsendInterval: ").append(sendInterval);
        stringBuffer.append("\n");
        logger.info(stringBuffer.toString());
    }

    public int getUdpServerListenPort() {
        return udpServerListenPort;
    }

    public void setUdpServerListenPort(int udpServerListenPort) {
        this.udpServerListenPort = udpServerListenPort;
    }

    public String getUdpMulticastNetwokinterface() {
        return udpMulticastNetwokinterface;
    }

    public void setUdpMulticastNetwokinterface(String udpMulticastNetwokinterface) {
        this.udpMulticastNetwokinterface = udpMulticastNetwokinterface;
    }

    public String getUdpMulticastHost() {
        return udpMulticastHost;
    }

    public void setUdpMulticastHost(String udpMulticastHost) {
        this.udpMulticastHost = udpMulticastHost;
    }

    public int getUdpMulticastPort() {
        return udpMulticastPort;
    }

    public void setUdpMulticastPort(int udpMulticastPort) {
        this.udpMulticastPort = udpMulticastPort;
    }

    public Sblx getSblx() {
        return sblx;
    }

    public void setSblx(Sblx sblx) {
        this.sblx = sblx;
    }

    public int getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(int sendInterval) {
        this.sendInterval = sendInterval;
    }
}
