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

    public static byte[] charToByteArray(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c >> 8) & 0xFF);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    public static byte[] shortToByteArray(short s) {
        byte[] b = new byte[2];
        b[0] = (byte) ((s >> 8) & 0xFF);
        b[1] = (byte) (s & 0xFF);
        return b;
    }

    public static byte[] longToByteArray(long l) {
        byte b[] = new byte[8];
        b[0] = (byte) (0xFF & (l >> 56));
        b[1] = (byte) (0xFF & (l >> 48));
        b[2] = (byte) (0xFF & (l >> 40));
        b[3] = (byte) (0xFF & (l >> 32));
        b[4] = (byte) (0xFF & (l >> 24));
        b[5] = (byte) (0xFF & (l >> 16));
        b[6] = (byte) (0xFF & (l >> 8));
        b[7] = (byte) (0xFF & l);
        return b;
    }

    public static void main(String[] args) {
        // https://www.cnblogs.com/yyyyfan/p/9351790.html
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
