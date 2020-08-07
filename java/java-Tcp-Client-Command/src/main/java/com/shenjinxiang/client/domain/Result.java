package com.shenjinxiang.client.domain;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 21:55
 */
public class Result {

    private boolean success;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
