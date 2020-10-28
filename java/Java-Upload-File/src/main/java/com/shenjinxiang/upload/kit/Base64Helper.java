package com.shenjinxiang.upload.kit;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class Base64Helper {

    public static String encode(String res){
        try{
            return new String(new Base64().encode(res.getBytes(StandardCharsets.UTF_8)));
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
            return new String(new Base64().decode(str.getBytes()),StandardCharsets.UTF_8);
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
