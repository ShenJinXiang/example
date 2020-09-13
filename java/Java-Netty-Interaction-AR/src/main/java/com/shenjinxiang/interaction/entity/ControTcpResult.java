package com.shenjinxiang.interaction.entity;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 23:07
 */
public class ControTcpResult {

    private String token;
    private boolean success;
    private String msg;

    public static ControTcpResult success(String token) {
        ControTcpResult result = new ControTcpResult();
        result.setMsg("OK");
        result.setToken(token);
        result.setSuccess(true);
        return result;
    }

    public static ControTcpResult error(String token, String msg) {
        ControTcpResult result = new ControTcpResult();
        result.setMsg(msg);
        result.setToken(token);
        result.setSuccess(false);
        return result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
