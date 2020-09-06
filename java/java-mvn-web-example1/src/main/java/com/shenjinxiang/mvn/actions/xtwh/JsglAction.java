package com.shenjinxiang.mvn.actions.xtwh;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.shenjinxiang.mvn.rapid.actions.RapidAction;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.domain.CurrentRyxx;
import com.shenjinxiang.mvn.rapid.interceptors.JsonResultInterceptor;
import com.shenjinxiang.mvn.services.xtwh.JsglService;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 22:58
 */
public class JsglAction extends RapidAction {

    @Inject
    private JsglService jsglService;

    public void index() {
        renderJsp("/WEB-INF/pages/xtwh/jsgl.jsp");
    }

    @Before(JsonResultInterceptor.class)
    public void queryJsbh() throws Exception {
        String jsbh = jsglService.queryJsbh();
        setJson(jsbh);
    }

    /**
     * 新增
     * @throws Exception
     */
    @Before(JsonResultInterceptor.class)
    public void saveJsxx() throws Exception {
        Bean params = getBean();
        CurrentRyxx currentRyxx = getCurrentRyxx();
        if (null != currentRyxx) {
            params.set("lrrid", currentRyxx.getRyid());
        }
        jsglService.saveJsxx(params);
    }

    /**
     * 修改
     * @throws Exception
     */
    @Before(JsonResultInterceptor.class)
    public void updateJsxx() throws Exception {
        Bean params = getBean();
        jsglService.updateJsxx(params);
    }

    /**
     * 查询
     * @throws Exception
     */
    @Before(JsonResultInterceptor.class)
    public void queryJsxx() throws Exception {
        int jsid = getParaToInt("jsid");
        Bean jsxx = jsglService.queryJsxx(jsid);
        setJson(jsxx);
    }

    /**
     * 删除角色
     * @throws Exception
     */
    @Before(JsonResultInterceptor.class)
    public void deleteJsxx() throws Exception {
        int jsid = getParaToInt("jsid");
        jsglService.deleteJsxx(jsid);
    }

    @Before(JsonResultInterceptor.class)
    public void saveJsZy() throws Exception {
        int jsid = getParaToInt("jsid");
        String zyids = getPara("zyids");
        if (StrKit.notBlank(zyids)) {
            String[] zyidArr = zyids.split(",");
            jsglService.saveJsZy(jsid, zyidArr);
        } else {
            jsglService.saveJsZy(jsid, null);
        }
        CacheKit.removeAll("permissions");
    }
}
