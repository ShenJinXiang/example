package com.shenjinxiang.mvn.rapid.core;

import com.jfinal.core.JFinal;
import com.shenjinxiang.mvn.rapid.consts.RapidConsts;

public class Rapid {

    private static Rapid rapid = null;

    static{
        rapid = new Rapid();
    }

    public static Rapid getRapidInstance(){
        return rapid;
    }

    private Rapid(){}


    @Override
    public String toString() {
        return "您现在使用的是Rapid平台，当前版本为："+ RapidConsts.RAPID_VERSION;
    }

    /**
     * 启动当前项目
     * @param webAppDir classes目录
     * @param port 端口
     * @param context 上下文
     * @param scanIntervalSeconds 代码刷新时间
     */
    public void start(String webAppDir, int port, String context, int scanIntervalSeconds){
        JFinal.start(webAppDir, port, context, scanIntervalSeconds);
    }
}
