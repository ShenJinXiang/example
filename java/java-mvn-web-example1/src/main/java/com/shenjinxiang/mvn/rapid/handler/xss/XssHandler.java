package com.shenjinxiang.mvn.rapid.handler.xss;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XssHandler extends Handler {
    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
        // 对于非静态文件，和非指定排除的url实现过滤
        request = new HttpServletRequestWrapper(request);
        nextHandler.handle(target, request, response, isHandled);
    }
}
