package com.shenjinxiang.transform.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.netty.channel.Channel;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 9:03
 */
public class TransformSend {

    @JsonIgnoreProperties
    private ConnType type = ConnType.TCP;
    private String ip;
    private int port;

    @JsonIgnoreProperties
    private Channel channel;

    public ConnType getType() {
        return type;
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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
