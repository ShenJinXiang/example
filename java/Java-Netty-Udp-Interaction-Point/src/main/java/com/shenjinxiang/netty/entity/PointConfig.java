package com.shenjinxiang.netty.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointConfig {

    private static final Logger logger = LoggerFactory.getLogger(PointConfig.class);

    private String multicastUdpNetwokInterface;
    private String multicastUdpIP;
    private int multicastUdpPort;
    private int udpListenPort;
    private int sendInterval;
    private Sblx sblx;

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

    public String getMulticastUdpNetwokInterface() {
        return multicastUdpNetwokInterface;
    }

    public void setMulticastUdpNetwokInterface(String multicastUdpNetwokInterface) {
        this.multicastUdpNetwokInterface = multicastUdpNetwokInterface;
    }

    public String getMulticastUdpIP() {
        return multicastUdpIP;
    }

    public void setMulticastUdpIP(String multicastUdpIP) {
        this.multicastUdpIP = multicastUdpIP;
    }

    public int getMulticastUdpPort() {
        return multicastUdpPort;
    }

    public void setMulticastUdpPort(int multicastUdpPort) {
        this.multicastUdpPort = multicastUdpPort;
    }

    public int getUdpListenPort() {
        return udpListenPort;
    }

    public void setUdpListenPort(int udpListenPort) {
        this.udpListenPort = udpListenPort;
    }

    public void log() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("配置信息：")
                .append("\n\tmulticastUdpNetwokInterface: ").append(multicastUdpNetwokInterface)
                .append("\n\tmulticastUdpIP: ").append(multicastUdpIP)
                .append("\n\tmulticastUdpPort: ").append(multicastUdpPort)
                .append("\n\tudpListenPort: ").append(udpListenPort)
                .append("\n\tsblx: ").append(sblx)
                .append("\n\tsendInterval: ").append(sendInterval)
                .append("\n");
        logger.info(stringBuffer.toString());
    }
}
