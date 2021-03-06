package com.shenjinxiang.mvn;

import com.jfinal.config.Routes;
import com.shenjinxiang.mvn.actions.xtwh.*;
import com.shenjinxiang.mvn.rapid.core.Rapid;
import com.shenjinxiang.mvn.rapid.core.RapidConfig;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/1 19:54
 */
public class Application extends RapidConfig {

    @Override
    public String configErrorMsg() {
        return null;
    }

    @Override
    protected void configAction(Routes routes) {
        // 系统维护
        routes.add("/xtwh/bmgl", BmglAction.class);
        routes.add("/xtwh/jsgl", JsglAction.class);
        routes.add("/xtwh/rygl", RyglAction.class);
        routes.add("/xtwh/zygl", ZyglAction.class);
    }

    public static void main(String[] args) throws IllegalAccessException {
        Rapid.getRapidInstance().start(8889, "/");
    }
}
