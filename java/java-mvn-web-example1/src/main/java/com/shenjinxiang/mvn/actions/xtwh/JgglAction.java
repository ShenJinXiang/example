package com.shenjinxiang.mvn.actions.xtwh;

import com.shenjinxiang.mvn.rapid.actions.RapidAction;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 22:56
 */
public class JgglAction extends RapidAction {

    public void index() {
        this.renderJsp("/WEB-INF/pages/xtwh/jggl.jsp");
    }
}
