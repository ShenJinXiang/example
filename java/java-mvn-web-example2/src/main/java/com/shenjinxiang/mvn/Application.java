package com.shenjinxiang.mvn;

import com.jfinal.config.Routes;
import com.jfinal.server.undertow.UndertowServer;
import com.shenjinxiang.mvn.actions.TestAction;
import com.shenjinxiang.rapid.core.RapidConfig;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/3 21:28
 */
public class Application extends RapidConfig {

    @Override
    public String configErrorMsg() {
        return "系统错误，请联系管理员";
    }

    @Override
    protected void configAction(Routes routes) {
        routes.add("/test", TestAction.class);
    }

    public static void main(String[] args) throws IllegalAccessException {
//        Rapid.getRapidInstance().start(8888, "/");
        UndertowServer.start(Application.class);
    }
}
