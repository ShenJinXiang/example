package com.shenjinxiang.mvn.actions.xtwh;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.ehcache.CacheKit;
import com.shenjinxiang.mvn.rapid.actions.RapidAction;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.interceptors.JsonResultInterceptor;
import com.shenjinxiang.mvn.rapid.shiro.RapidShiroInterceptor;
import com.shenjinxiang.mvn.services.xtwh.ZyglService;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 21:37
 */
@Before(JsonResultInterceptor.class)
public class ZyglAction extends RapidAction {

    @Inject
    private ZyglService zyglService;

    @Clear(JsonResultInterceptor.class)
    public void index() {
        bindCurrentRyxxToPage();
        renderJsp("/WEB-INF/pages/xtwh/zygl.jsp");
    }

    public void saveZYXX() throws Exception {
        Bean zyxx = this.getBean();
        zyglService.saveZyxx(zyxx);
        RapidShiroInterceptor.resetUrl();
    }



    public void queryZYXX() throws Exception {
        String zyid = this.getPara("zyid");
        Bean zyxx = zyglService.queryZyxx(zyid);
        this.setJson(zyxx);
    }

    public void delete() throws Exception {
        String zyid = this.getPara("zyid");
        zyglService.delete(zyid);
        RapidShiroInterceptor.resetUrl();
    }

    public void updateZYXX() throws Exception {
        Bean zyxx = this.getBean();
        zyglService.updateZyxx(zyxx);
        RapidShiroInterceptor.resetUrl();
        CacheKit.removeAll("permissions");
    }

    public void queryZyxxForTree() throws Exception {
        List<Bean> zyxxList = zyglService.queryZyxxForTree();
        this.setJson(zyxxList);
    }

    public void queryJsZy() throws Exception {
        int jsid = getParaToInt("jsid");
        List<Bean> list = zyglService.queryJsZy(jsid);
        setJson(list);
    }
}