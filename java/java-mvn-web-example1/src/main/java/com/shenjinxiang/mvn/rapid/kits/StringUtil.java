package com.shenjinxiang.mvn.rapid.kits;

import com.shenjinxiang.mvn.rapid.consts.RapidConsts;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/6 15:12
 */
public class StringUtil {

    public static String getMD5_SALT(String password) {
        return stringToMD5(password, RapidConsts.MD5_SALT);
    }

    private static String stringToMD5(String str, Object salt) {
        return (new Md5Hash(str, salt)).toString();
    }
}
