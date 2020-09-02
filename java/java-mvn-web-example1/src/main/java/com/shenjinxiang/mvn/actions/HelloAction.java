package com.shenjinxiang.mvn.actions;

import com.jfinal.core.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/1 19:55
 */
public class HelloAction extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(HelloAction.class);

    public void index() {
        logger.info("index");
        renderText("hello mvn-web");
    }

    public void page() {
        logger.info("page");
        renderJsp("/WEB-INF/pages/hello.jsp");
    }
}
