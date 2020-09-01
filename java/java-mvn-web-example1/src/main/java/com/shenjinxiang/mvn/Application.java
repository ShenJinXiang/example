package com.shenjinxiang.mvn;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.shenjinxiang.mvn.actions.HelloAction;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/1 19:54
 */
public class Application extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setViewType(ViewType.JSP);
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/hello", HelloAction.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }

    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 8888, "/", 5);
    }
}
