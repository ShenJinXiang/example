package com.shenjinxiang.mvn.actions;

import com.jfinal.core.Controller;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/1 19:55
 */
public class HelloAction extends Controller {

    public void index() {
        renderText("hello mvn-web");
    }

    public void page() {
        renderJsp("/WEB-INF/pages/hello.jsp");
    }
}
