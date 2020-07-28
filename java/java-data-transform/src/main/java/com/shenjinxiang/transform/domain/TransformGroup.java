package com.shenjinxiang.transform.domain;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 8:32
 */
public class TransformGroup {

    private boolean enable;
    private TransformReceive receive;
    private List<TransformSend> send;

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

    public List<TransformSend> getSend() {
        return send;
    }

    public void setSend(List<TransformSend> send) {
        this.send = send;
    }
}
