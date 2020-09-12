package com.shenjinxiang.interaction.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CentralConfig {

    private static Logger logger = LoggerFactory.getLogger(CentralConfig.class);

    private int maxTcpDataLength;
    private int qtServerListenPort;
    private String arServerHost;
    private int arServerPort;
    private String algServerHost;
    private int algServerPort;
    private int algFixedDataLength;
    private int udpServerListenPort;
    private String udpMulticastNetwokinterface;
    private String udpMulticastHost;
    private int udpMulticastPort;
    private String ddFilePath;
    private List<Target> targets;

    public void log() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("配置信息：")
                .append("\n\tmaxTcpDataLength: ").append(maxTcpDataLength)
                .append("\n\tqtServerListenPort: ").append(qtServerListenPort)
                .append("\n\tarServerHost: ").append(arServerHost)
                .append("\n\tarServerPort: ").append(arServerPort)
                .append("\n\talgServerHost: ").append(algServerHost)
                .append("\n\talgServerPort: ").append(algServerPort)
                .append("\n\talgFixedDataLength: ").append(algFixedDataLength)
                .append("\n\tudpServerListenPort: ").append(udpServerListenPort)
                .append("\n\tudpMulticastNetwokinterface: ").append(udpMulticastNetwokinterface)
                .append("\n\tudpMulticastHost: ").append(udpMulticastHost)
                .append("\n\tudpMulticastPort: ").append(udpMulticastPort)
                .append("\n\ttargets: ");
        for (Target target: targets) {
            stringBuffer.append("\n\t\t").append(target.toString());
        }
        stringBuffer.append("\n");
        logger.info(stringBuffer.toString());
    }

    public int getMaxTcpDataLength() {
        return maxTcpDataLength;
    }

    public void setMaxTcpDataLength(int maxTcpDataLength) {
        this.maxTcpDataLength = maxTcpDataLength;
    }

    public int getQtServerListenPort() {
        return qtServerListenPort;
    }

    public void setQtServerListenPort(int qtServerListenPort) {
        this.qtServerListenPort = qtServerListenPort;
    }

    public String getArServerHost() {
        return arServerHost;
    }

    public void setArServerHost(String arServerHost) {
        this.arServerHost = arServerHost;
    }

    public int getArServerPort() {
        return arServerPort;
    }

    public void setArServerPort(int arServerPort) {
        this.arServerPort = arServerPort;
    }

    public String getAlgServerHost() {
        return algServerHost;
    }

    public void setAlgServerHost(String algServerHost) {
        this.algServerHost = algServerHost;
    }

    public int getAlgServerPort() {
        return algServerPort;
    }

    public void setAlgServerPort(int algServerPort) {
        this.algServerPort = algServerPort;
    }

    public int getAlgFixedDataLength() {
        return algFixedDataLength;
    }

    public void setAlgFixedDataLength(int algFixedDataLength) {
        this.algFixedDataLength = algFixedDataLength;
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

    public String getDdFilePath() {
        return ddFilePath;
    }

    public void setDdFilePath(String ddFilePath) {
        this.ddFilePath = ddFilePath;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public void setUdpMulticastPort(int udpMulticastPort) {
        this.udpMulticastPort = udpMulticastPort;
    }
}
