package com.shenjinxiang.mvn.actions;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.shenjinxiang.mvn.services.TestService;
import com.shenjinxiang.rapid.actions.RapidAction;
import com.shenjinxiang.rapid.domain.Bean;
import com.shenjinxiang.rapid.interceptors.JsonResultInterceptor;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/3 21:38
 */
public class TestAction extends RapidAction {

    @Inject
    private TestService testService;

    public void index() {
        renderText("hello TEST!");
    }

    @Before(JsonResultInterceptor.class)
    public void list() {
        List<Bean> list = testService.queryAllZyList();
        setJson(list);
    }

}
