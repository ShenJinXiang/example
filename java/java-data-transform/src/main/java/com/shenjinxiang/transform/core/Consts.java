package com.shenjinxiang.transform.core;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 16:10
 */
public class Consts {

    private static boolean devMode = true;

    public static boolean isDevMode() {
        return devMode;
    }

    public static void setDevMode(boolean devMode) {
        Consts.devMode = devMode;
    }
}
