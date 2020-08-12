package com.shenjinxiang.client.kit;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 17:09
 */
public class ByteKit {

    public static byte[] intToByteArray(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((i >> 24) & 0xFF);
        bytes[1] = (byte) ((i >> 16) & 0xFF);
        bytes[2] = (byte) ((i >> 8) & 0xFF);
        bytes[3] = (byte) (i & 0xFF);
        return bytes;
    }

    public static void main(String[] args) {
        byte[] bytes1 = intToByteArray(1);
        byte[] bytes2 = intToByteArray(2);
        byte[] bytes3 = intToByteArray(10);
        byte[] bytes4 = intToByteArray(100);
        byte[] bytes5 = intToByteArray(1000);
        byte[] bytes6 = intToByteArray(10000);
        byte[] bytes7 = intToByteArray(100000);
        byte b1 = 1;
        byte b2 = 127;
        Byte b3 = (byte)128;
        int b3int = b3.intValue();
        int b3int1 = Byte.toUnsignedInt(b3);
        System.out.println(b3int);
        System.out.println(b3int1);
        System.out.println();
    }
}
