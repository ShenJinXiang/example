package com.shenjinxiang.exam.rxtx;

import com.shenjinxiang.kit.ByteArrayConveter;
import com.shenjinxiang.kit.ByteKit;

public class Demo1 {

    public static void main(String[] args) {
        // AA 02 19 AD
        // 00 00 00 00
        // FE FF FF 7F
        // 07 97 07 B6
        // 48
        // 55
        // 01
        String str = "AA0219AD00000000FEFFFF7F079707B6485501";
        byte[] bytes = ByteKit.hexStrToByteArray(str);
        byte led1 = bytes[4];
        byte led2 = bytes[5];
        byte led3 = bytes[6];
        byte led4 = bytes[7];

        byte press1 = bytes[8];
        byte press2 = bytes[9];
        byte press3 = bytes[10];
        byte press4 = bytes[11];

        String led1Str = getBit(led1);
        String led2Str = getBit(led2);
        String led3Str = getBit(led3);
        String led4Str = getBit(led4);
        String press1Str = getBit(press1);
        String press2Str = getBit(press2);
        String press3Str = getBit(press3);
        String press4Str = getBit(press4);
        System.out.println(led1Str);
        System.out.println(led2Str);
        System.out.println(led3Str);
        System.out.println(led4Str);
        System.out.println(press1Str);
        System.out.println(press2Str);
        System.out.println(press3Str);
        System.out.println(press4Str);

        // 07 97 07 B6
        byte[] bytes1 = new byte[]{(byte)0x00, (byte)0x00, (byte)0x07, (byte)0x97};
        byte[] bytes2 = new byte[]{(byte)0x00, (byte)0x00, (byte)0x07, (byte)0xB6};
        byte[] bytes3 = new byte[]{(byte)0x00, (byte)0x00, bitToByte("01111111"), bitToByte("11111111")};
        int int1 = ByteArrayConveter.getInt(bytes1, 0);
        int int2 = ByteArrayConveter.getInt(bytes2, 0);
        int int3 = ByteArrayConveter.getInt(bytes3, 0);
        System.out.println(int1);
        System.out.println(int2);
        System.out.println(int3);

        byte[] bytes4 = ByteArrayConveter.getByteArray(4095);
        System.out.println(ByteKit.byteArrayToHexStr(bytes4));

        byte[] bytes5 = ByteArrayConveter.getByteArray(2047);
        System.out.println(ByteKit.byteArrayToHexStr(bytes5));
    }

    public static String getBit(byte by) {
        StringBuffer sb = new StringBuffer();
        sb.append((by>>7)&0x1)
                .append((by>>6)&0x1)
                .append((by>>5)&0x1)
                .append((by>>4)&0x1)
                .append((by>>3)&0x1)
                .append((by>>2)&0x1)
                .append((by>>1)&0x1)
                .append((by>>0)&0x1);
        return sb.toString();
    }

    public static byte bitToByte(String bit) {
        int re, len;
        if (null == bit) {
            return 0;
        }
        len = bit.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        if (len == 8) {// 8 bit处理
            if (bit.charAt(0) == '0') {// 正数
                re = Integer.parseInt(bit, 2);
            } else {// 负数
                re = Integer.parseInt(bit, 2) - 256;
            }
        } else {//4 bit处理
            re = Integer.parseInt(bit, 2);
        }
        return (byte) re;
    }
}
