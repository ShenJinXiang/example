package com.shenjinxiang.interaction.entity;

import java.net.InetSocketAddress;

public class Target {

    private String udpHost;
    private int udpPort;
    private Sblx sblx;
    private InetSocketAddress address;

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{").append("sblx: " + sblx + ", udpHost: " + udpHost + ", udpPort: " + udpPort + "}");
        return stringBuffer.toString();
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }

    public String getUdpHost() {
        return udpHost;
    }

    public void setUdpHost(String udpHost) {
        this.udpHost = udpHost;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    public Sblx getSblx() {
        return sblx;
    }

    public void setSblx(Sblx sblx) {
        this.sblx = sblx;
    }
}
