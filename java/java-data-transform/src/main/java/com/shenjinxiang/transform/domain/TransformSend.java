package com.shenjinxiang.transform.domain;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 9:03
 */
public class TransformSend {

    private ConnType type;
    private String ip;
    private int port;

    public ConnType getType() {
        return type;
    }

    public void setType(ConnType type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
