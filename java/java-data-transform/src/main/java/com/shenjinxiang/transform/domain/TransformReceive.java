package com.shenjinxiang.transform.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 8:33
 */
public class TransformReceive {

    private ConnType type;
    private String ip;
    private int port;
    private String dataHandler;

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

    public String getDataHandler() {
        return dataHandler;
    }

    public void setDataHandler(String dataHandler) {
        this.dataHandler = dataHandler;
    }


}
