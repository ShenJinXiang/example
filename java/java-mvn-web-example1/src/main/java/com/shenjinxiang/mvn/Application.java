package com.shenjinxiang.mvn;

import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.shenjinxiang.mvn.actions.HelloAction;
import com.shenjinxiang.mvn.rapid.core.RapidConfig;
import com.shenjinxiang.mvn.rapid.kits.PathKit;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/1 19:54
 */
public class Application extends RapidConfig {

    @Override
    public String configErrorMsg() {
        return null;
    }

    @Override
    protected void configAction(Routes routes) {
        routes.add("/hello", HelloAction.class);
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(PathKit.getCurrentPath());
        System.out.println(PathKit.getCrrentFilePath());
//        String rootDir = "E:/shenjinxiang/git_xm/example/java/java-mvn-web-example1/";
        String rootDir = "/Users/shenjinxiang/shenjinxiang/git_xm/example/java/java-mvn-web-example1/";
        JFinal.start(rootDir + "src/main/webapp", 8888, "/", 5);
    }
}
