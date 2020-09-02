package com.shenjinxiang.mvn.actions.xtwh;

import com.shenjinxiang.mvn.rapid.actions.RapidAction;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 22:58
 */
public class JsglAction extends RapidAction {

    public void index() {
        renderJsp("/WEB-INF/pages/xtwh/jsgl.jsp");
    }
}
