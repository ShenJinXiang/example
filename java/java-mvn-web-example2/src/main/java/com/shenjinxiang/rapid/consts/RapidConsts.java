package com.shenjinxiang.rapid.consts;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/1 19:54
 */
public final class RapidConsts {

    public final static String RAPID_NAME = "RAPID平台";

    public final static String RAPID_VERSION = "1.0";

    public static boolean IS_DEV_MODE = false;

    public final static int MAX_POST_SIZE = 50 * 1024 * 1024;

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

}
