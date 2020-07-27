package com.shenjinxiang.transform.core;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 16:10
 */
public class Consts {

    public static final String ENCODE = "UTF-8";

    /**
     * 是否jar包运行
     */
    private static boolean jar = false;

    /**
     * 是否dev模式
     */
    private static boolean devMode = true;

    private static String currentPath;

    public static boolean isDevMode() {
        return devMode;
    }

    public static void setDevMode(boolean devMode) {
        Consts.devMode = devMode;
    }

    public static boolean isJar() {
        return jar;
    }

    public static void setJar(Boolean isJar) {
        jar = isJar;
    }

    public static String getCurrentPath() {
        return currentPath;
    }

    public static void setCurrentPath(String path) {
        currentPath = path;
    }
}
