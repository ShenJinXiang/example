package com.shenjinxiang.mvn;

import com.jfinal.config.Routes;
import com.shenjinxiang.mvn.actions.HelloAction;
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
        routes.add("/hello", HelloAction.class);
    }

    public static void main(String[] args) throws IllegalAccessException {
        Rapid.getRapidInstance().start(8888, "/");
    }
}
