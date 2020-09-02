package com.shenjinxiang.mvn.rapid.core;

import com.jfinal.core.JFinal;
import com.shenjinxiang.mvn.rapid.consts.RapidConsts;
import com.shenjinxiang.mvn.rapid.kits.PathKit;

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
    public void start(int port, String context) throws IllegalAccessException {
        String targetPath = PathKit.getCurrentPath();
        String contextDir = targetPath.substring(0, targetPath.lastIndexOf("target"));
        String webAppDir = contextDir + "src/main/webapp";
        JFinal.start(webAppDir, port, context, 5);
    }
}
