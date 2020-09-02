package com.shenjinxiang.mvn.rapid.core;

import com.jfinal.config.*;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.shenjinxiang.mvn.rapid.consts.RapidConsts;

public abstract class RapidConfig extends JFinalConfig {

    private Routes routes;
    public abstract String configErrorMsg();
    protected abstract void configAction(Routes routes);

    @Override
    public void configConstant(Constants constants) {
        constants.setError401View("/WEB-INF/pages/common/401.html");
        constants.setError403View("/WEB-INF/pages/common/403.html");
        constants.setError404View("/WEB-INF/pages/common/404.html");
        constants.setError500View("/WEB-INF/pages/common/500.html");
        constants.setMaxPostSize(50 * 1024 * 1024);

        constants.setDevMode(RapidConsts.IS_DEV_MODE);
        constants.setViewType(ViewType.JSP);
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

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
