package com.shenjinxiang.appjk.entity;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/4 21:39
 */
public class Result {

    private boolean success;
    private String msg;
    private String paramStr;
    private String param;
    private String resultStr;
    private String result;

    public static Result success(String paramStr, String param, String resultStr, String result) {
        Result result1 = new Result();
        result1.success = true;
        result1.paramStr = paramStr;
        result1.param = param;
        result1.resultStr = resultStr;
        result1.result = result;
        return result1;
    }

    public static Result error(String msg) {
        Result result1 = new Result();
        result1.success = false;
        result1.msg = msg;
        return result1;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getParamStr() {
        return paramStr;
    }

    public void setParamStr(String paramStr) {
        this.paramStr = paramStr;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
