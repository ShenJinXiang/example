package com.shenjinxiang.mvn.rapid.consts;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/1 19:54
 */
public final class RapidConsts {

    public final static String RAPID_NAME = "平台名称";

    public final static String RAPID_VERSION = "1.0";

    public final static boolean IS_ALL_SCR = false;

    public static String LOGIN_URI = "login";

    public static boolean IS_DEV_MODE = false;

    public final static String MD5_SALT = "SHENJX_MVNRAPID_2020";

    private static String ERROR_MSG = "操作失败，请联系管理员！";

    public static String getErrorMsg() {
        return ERROR_MSG;
    }

    public static void setErrorMsg(String errorMsg) {
        ERROR_MSG = errorMsg;
    }
}
