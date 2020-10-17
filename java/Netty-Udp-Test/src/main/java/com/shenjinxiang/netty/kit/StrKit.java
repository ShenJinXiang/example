package com.shenjinxiang.netty.kit;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/6/3 9:14
 */
public class StrKit {

    public static boolean notBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String str) {
        return null == str || "".equals(str);
    }

    public static String getStrVal(Object str) {
        if (null == str || "".equals(str)) {
            return "";
        }
        return String.valueOf(str);
    }
}
