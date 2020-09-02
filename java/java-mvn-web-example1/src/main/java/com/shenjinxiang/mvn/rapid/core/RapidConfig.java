package com.shenjinxiang.mvn.rapid.core;

import com.jfinal.config.*;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.shenjinxiang.mvn.rapid.consts.RapidConsts;
import com.shenjinxiang.mvn.rapid.handler.xss.XssHandler;
import com.shenjinxiang.mvn.rapid.plugin.druid.DruidStatViewHandler;
import com.shenjinxiang.mvn.rapid.plugin.mybatis.DbSourcePlugin;
import com.shenjinxiang.mvn.rapid.shiro.RapidShiroInterceptor;

import java.net.URL;

public abstract class RapidConfig extends JFinalConfig {

    private Routes routes;
    public abstract String configErrorMsg();
    protected abstract void configAction(Routes routes);

    @Override
    public void configConstant(Constants constants) {
        constants.setReportAfterInvocation(true);
        constants.setError401View("/WEB-INF/pages/common/401.html");
        constants.setError403View("/WEB-INF/pages/common/403.html");
        constants.setError404View("/WEB-INF/pages/common/404.html");
        constants.setError500View("/WEB-INF/pages/common/500.html");
        constants.setMaxPostSize(RapidConsts.MAX_POST_SIZE);
        // 读取rapid配置
        loadPropertyFile("rapid.properties");
        RapidConsts.setIsAllScr(getPropertyToBoolean("isAllScr"));
        RapidConsts.IS_DEV_MODE = getPropertyToBoolean("devMode");
        constants.setDevMode(RapidConsts.IS_DEV_MODE);
        constants.setViewType(ViewType.JSP);
        // 全局错误提示
        RapidConsts.setErrorMsg(configErrorMsg());
    }


    @Override
    public void configRoute(Routes routes) {
        this.routes = routes;
        configAction(this.routes);
    }


    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        URL url = getClass().getResource("ehcache.xml");
        plugins.add(new EhCachePlugin(url));
        DbSourcePlugin.configPlugin(plugins);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new RapidShiroInterceptor());
    }

    @Override
    public void configHandler(Handlers handlers) {
        //druid监控
        DruidStatViewHandler dvh =  new DruidStatViewHandler("/druid");
        handlers.add(dvh);
        handlers.add(new XssHandler());
    }
}
