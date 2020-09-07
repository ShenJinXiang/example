package com.shenjinxiang.netty.core;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/5 19:42
 */
public class LocalAddress {

    private NetworkInterface networkInterface;
    private InetAddress inetAddress;

    public NetworkInterface getNetworkInterface() {
        return networkInterface;
    }

    public void setNetworkInterface(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    @Override
    public String toString() {
        return "{name: " + this.getNetworkInterface().getName() + ", ip: " + this.inetAddress.getHostAddress() + "}";
    }
}
