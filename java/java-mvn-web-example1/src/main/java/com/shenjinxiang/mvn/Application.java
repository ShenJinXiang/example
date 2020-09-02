package com.shenjinxiang.mvn;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.shenjinxiang.mvn.actions.HelloAction;
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

    public static void main(String[] args) {
        JFinal.start("E:/shenjinxiang/git_xm/example/java/java-mvn-web-example1/src/main/webapp", 8888, "/", 5);
    }
}
