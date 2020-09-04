package com.shenjinxiang.appjk.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Helper {

    private static final String CHARSET = "UTF-8";

    public static String encode(String res){
        try{
            return new String(new Base64().encode(res.getBytes(CHARSET)));
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encode(byte[] res){
        try{
            return new Base64().encode(res);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decode(String str) {
        try{
            return new String(new Base64().decode(str.getBytes()),CHARSET);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decode(byte[] str) {
        try {
            return new Base64().decode(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
