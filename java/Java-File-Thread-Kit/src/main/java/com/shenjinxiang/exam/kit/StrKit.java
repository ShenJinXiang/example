package com.shenjinxiang.exam.kit;

import java.util.Random;

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

    private static char randomChar(char[] chars) {
        Random random = new Random();
        return chars[random.nextInt(chars.length)];
    }

    private static String rnadom(char[] chars, int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(randomChar(chars));
        }
        return stringBuffer.toString();
    }

    public static String randomDigit(int length) {
        char[] digit = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        return rnadom(digit, length);
    }

}
