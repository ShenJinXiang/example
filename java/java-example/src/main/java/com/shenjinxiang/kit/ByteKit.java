package com.shenjinxiang.kit;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 17:09
 */
public class ByteKit {

    public static byte[] asBytes(byte[]... bytes) {
        ByteBuf buffer = Unpooled.buffer();
        for (byte[] byteArr: bytes) {
            buffer.writeBytes(byteArr);
        }
        byte[] message = new byte[buffer.readableBytes()];
        buffer.readBytes(message);
        return message;
    }

    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStrToByteArray(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++) {
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }



    public static void main(String[] args) throws IOException {
        // https://www.cnblogs.com/yyyyfan/p/9351790.html
//        byte[] bytes = ByteArrayConveter.getByteArray((short)18486);
//        System.out.println(byteArrayToHexStr(bytes));
//        byte[] bytes1 = ByteArrayConveter.getByteArray(82259);
//        System.out.println(byteArrayToHexStr(bytes1));
//        System.out.println(ByteArrayConveter.getInt(hexStrToByteArray(byteArrayToHexStr(bytes1)), 0));

        String str1 = "AA02466B20201090FFFF1D4907BA5455F8";
        byte[] bytes = ByteKit.hexStrToByteArray(str1);
        System.out.println(Arrays.toString(bytes));
        for(byte b: bytes) {
            int i = b & 0xFF;
        }
    }


}
