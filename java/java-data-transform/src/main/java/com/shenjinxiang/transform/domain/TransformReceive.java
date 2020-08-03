package com.shenjinxiang.transform.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shenjinxiang.transform.handler.DataHandler;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 8:33
 */
public class TransformReceive {

    private ConnType type;
    private int port;
    private String dataHandler;
    private DataHandler handler;

    public ConnType getType() {
        return type;
    }

    public void setType(ConnType type) {
        this.type = type;
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

    public void setDataHandler(String dataHandler) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.dataHandler = dataHandler;
        Class handlerClass = Class.forName(dataHandler);
        this.handler = (DataHandler) handlerClass.newInstance();
    }

    public DataHandler getHandler() {
        return handler;
    }
}
