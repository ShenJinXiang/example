package com.shenjinxiang.mvn.actions.xtwh;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.shenjinxiang.mvn.rapid.actions.RapidAction;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.domain.CurrentRyxx;
import com.shenjinxiang.mvn.rapid.interceptors.JsonResultInterceptor;
import com.shenjinxiang.mvn.services.xtwh.RyglService;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 22:58
 */
@Before(JsonResultInterceptor.class)
public class RyglAction extends RapidAction {

    @Inject
    private RyglService ryglService;

    @Clear(JsonResultInterceptor.class)
    public void index() {
        bindCurrentRyxxToPage();
        renderJsp("/WEB-INF/pages/xtwh/rygl.jsp");
    }

    public void queryRybh() throws Exception {
        String rybh = ryglService.queryRybh();
        setJson(rybh);
    }

    public void queryRyxx() throws Exception {
        int ryid = getParaToInt("ryid");
        Bean ryxx = ryglService.queryRyxx(ryid);
        setJson(ryxx);
    }

    public void saveRyxx() throws Exception {
        Bean params = getBean();
        CurrentRyxx currentRyxx = getCurrentRyxx();
        if (null != currentRyxx) {
            params.set("lrrid", currentRyxx.getRyid());
        }
        ryglService.saveRyxx(params);
    }

    public void updateRyxx() throws Exception {
        Bean params = getBean();
        ryglService.updateRyxx(params);
    }

    /**
     * 删除
     * @throws Exception
     */
    public void delRyxx() throws Exception {
        int ryid = getParaToInt("ryid");
        ryglService.delRyxx(ryid);
        CacheKit.remove("permissions", ryid);
    }

    /**
     * 重置密码
     * @throws Exception
     */
    public void resetPwd() throws Exception {
        int ryid = getParaToInt("ryid");
        ryglService.resetPwd(ryid);
    }

    public void saveRyJs() throws Exception {
        int ryid = getParaToInt("ryid");
        String jsids = this.getPara("jsids");
        if (StrKit.notBlank(jsids)) {
            String[] jsidArr = jsids.split(",");
            ryglService.saveRyJs(ryid, jsidArr);
        } else {
            ryglService.saveRyJs(ryid, null);
        }
        CacheKit.remove("permissions", ryid);
    }

    public void queryJsForTree() throws Exception {
        int ryid = getParaToInt("ryid");
        List<Bean> list = ryglService.queryJsForTree(ryid);
        setJson(list);
    }

    public void jyry() throws Exception {
        int ryid = getParaToInt("ryid");
        ryglService.jyry(ryid);
    }

    public void qyry() throws Exception {
        int ryid = getParaToInt("ryid");
        ryglService.qyry(ryid);
    }
}
