package com.shenjinxiang.mvn.rapid.consts;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/1 19:54
 */
public final class RapidConsts {

    public final static String RAPID_NAME = "平台名称";

    public final static String RAPID_VERSION = "1.0";

    public static final int MAX_EXCEL_ROWS = 4000;

    public static String LOGIN_URI = "login";

    public static boolean IS_DEV_MODE = false;

    public final static String MD5_SALT = "SHENJX_MVNRAPID_2020";

    public final static int MAX_POST_SIZE = 50 * 1024 * 1024;

    private static boolean IS_ALL_SCR = false;

    private static String ERROR_MSG = "操作失败，请联系管理员！";

    private static String DEFAULT_DBSOURCE_KEY;

    public static void setDEFAULT_DBSOURCE_KEY(String default_dbsource_key) {
        DEFAULT_DBSOURCE_KEY = default_dbsource_key;
    }

    public static String getDEFAULT_DBSOURCE_KEY() {
        return DEFAULT_DBSOURCE_KEY;
    }


    public static String getErrorMsg() {
        return ERROR_MSG;
    }

    public static void setErrorMsg(String errorMsg) {
        ERROR_MSG = errorMsg;
    }

    public static boolean isIsAllScr() {
        return IS_ALL_SCR;
    }

    public static void setIsAllScr(boolean isAllScr) {
        IS_ALL_SCR = isAllScr;
    }
}
