package com.shenjinxiang.appjk.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Created by shenjinxiang on 2017-04-19.
 */
public class DESUtil {
    private final static String DES = "DES";
    private final static String ENCODE = "UTF-8";
    private final static String defaultKey = "[#^$c!s`";

    /**
     * 使用 默认key 加密
     */
    public static String encrypt(String data) {
        return encrypt(data, defaultKey);
    }

    /**
     * 使用 默认key 解密
     *
     */
    public static String decrypt(String data) {
        return decrypt(data, defaultKey);
    }

    /**
     * 根据键值进行加密
     */
    public static String encrypt(String data, String key) {
        try {
            byte[] bt = encrypt(data.getBytes(ENCODE), key.getBytes(ENCODE));
            byte [] bytes = Base64Helper.encode(bt);
            if (null == bytes) {
                return null;
            }
            return new String(bytes, ENCODE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据键值进行解密
     */
    public static String decrypt(String data, String key) {
        try {
            if (data == null)
                return null;
            byte[] buf = Base64Helper.decode(data.getBytes(ENCODE));
            byte[] bt = decrypt(buf, key.getBytes(ENCODE));
            return new String(bt, ENCODE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据键值进行加密
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * 根据键值进行解密
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        String str = "sdfsdlfjsldfjl 设计费凉快圣诞节sdfsdfds对是的发送到士大夫士大夫士大夫收到士大夫士大夫阿斯蒂芬阿斯蒂芬阿士大夫士大夫阿斯蒂芬水电费水电费都发阿道夫士大夫奥德赛嘎嘎嘎过阿士大夫撒旦法 搭嘎大法师打发士大夫沃尔沃额啊啊给的食物而未发士大夫士大夫弗兰克斯登记法律上的来说";
        String str1 = DESUtil.encrypt(str);
        System.out.println(str1);
        String str2 = DESUtil.decrypt(str1);
        System.out.println(str2);

    }
}
