package com.shenjinxiang.mvn.rapid.handler.xss;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class HttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper {
    public HttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 重写并过滤getParameter方法
     */
    @Override
    public String getParameter(String name) {
        return HtmlFilter.getBasicHtmlandimage(super.getParameter(name));
    }

    /**
     * 重写并过滤getParameterValues方法
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (null == values) {
            return null;
        }
        for (int i = 0; i < values.length; i++) {
            values[i] = HtmlFilter.getBasicHtmlandimage(values[i]);
        }
        return values;
    }

    /**
     * 重写并过滤getParameterMap方法
     */
    @Override
    public Map getParameterMap() {
        Map<String, String[]> paraMap = super.getParameterMap();
        // 对于paraMap为空的直接return
        if (null == paraMap || paraMap.isEmpty()) {
            return paraMap;
        }
        for (Map.Entry<String, String[]> entry : paraMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (null == values) {
                continue;
            }
            String[] newValues = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                newValues[i] = HtmlFilter.getBasicHtmlandimage(values[i]);
            }
            paraMap.put(key, newValues);
        }
        return paraMap;
    }
}
