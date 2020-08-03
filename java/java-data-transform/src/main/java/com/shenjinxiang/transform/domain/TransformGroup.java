package com.shenjinxiang.transform.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shenjinxiang.transform.handler.DataHandler;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 8:32
 */
public class TransformGroup {

    private boolean enable;
    private TransformReceive receive;
    @JsonProperty("send")
    private List<TransformSend> sendList;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public TransformReceive getReceive() {
        return receive;
    }

    public void setReceive(TransformReceive receive) {
        this.receive = receive;
    }

    public List<TransformSend> getSendList() {
        return sendList;
    }

    public void setSendList(List<TransformSend> sendList) {
        this.sendList = sendList;
    }
}
