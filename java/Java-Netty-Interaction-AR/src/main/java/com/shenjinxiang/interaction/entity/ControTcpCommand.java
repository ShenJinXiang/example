package com.shenjinxiang.interaction.entity;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 23:55
 */
public class ControTcpCommand {

    private String token;
    private String command;
    private String ksid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getKsid() {
        return ksid;
    }

    public void setKsid(String ksid) {
        this.ksid = ksid;
    }
}
