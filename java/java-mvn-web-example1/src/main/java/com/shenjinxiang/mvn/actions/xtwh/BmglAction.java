package com.shenjinxiang.mvn.actions.xtwh;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.shenjinxiang.mvn.rapid.actions.RapidAction;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.domain.CurrentRyxx;
import com.shenjinxiang.mvn.rapid.interceptors.JsonResultInterceptor;
import com.shenjinxiang.mvn.services.xtwh.BmglService;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/5 21:28
 */
public class BmglAction extends RapidAction {

    @Inject
    private BmglService bmglService;

    public void index() {
        renderJsp("/WEB-INF/pages/xtwh/bmgl.jsp");
    }

    /**
     * 获取部门编号
     * @throws Exception
     */
    @Before(JsonResultInterceptor.class)
    public void queryBmbh() throws Exception {
        String bmbh = bmglService.queryBmbh();
        setJson(bmbh);
    }

    /**
     * 新增
     * @throws Exception
     */
    @Before(JsonResultInterceptor.class)
    public void saveBmxx() throws Exception {
        Bean params = getBean();
        CurrentRyxx currentRyxx = getCurrentRyxx();
        if (null != currentRyxx) {
            params.set("lrrid", currentRyxx.getRyid());
        }
        bmglService.saveBmxx(params);
    }

    /**
     * 修改
     * @throws Exception
     */
    @Before(JsonResultInterceptor.class)
    public void updateBmxx() throws Exception {
        Bean params = getBean();
        bmglService.updateBmxx(params);
    }

    @Before(JsonResultInterceptor.class)
    public void queryBmxx() throws Exception {
        String bmid = getPara("bmid");
        Bean bmxx = bmglService.queryBmxx(bmid);
        setJson(bmxx);
    }

    @Before(JsonResultInterceptor.class)
    public void deleteBmxx() throws Exception {
        String bmid = getPara("bmid");
        bmglService.deleteBmxx(bmid);
    }
}
