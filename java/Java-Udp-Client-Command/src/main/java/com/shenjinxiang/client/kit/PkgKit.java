package com.shenjinxiang.client.kit;

import java.util.Calendar;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 20:48
 */
public class PkgKit {

    public static BytePackage package512(int count) {
        byte[] data = ByteArrayConveter.getByteArray(count);
        BytePackage bytePackage = BytePackage.builder()
                .mId((short) 512)
                .sId(1234)
                .dId(1234)
                .bId(1234)
                .no(count)
                .flag((byte)0)
                .reser(0)
                .date(getDate())
                .time(getTime())
                .dataLen((short) data.length)
                .data(data)
                .build();
        return bytePackage;
    }

    public static BytePackage package513(int xh) {
        int time = getTime();
        byte[] data = ByteArrayConveter.getByteArray(time);
        BytePackage bytePackage = BytePackage.builder()
                .mId((short) 512)
                .sId(1234)
                .dId(1234)
                .bId(1234)
                .no(xh)
                .flag((byte)0)
                .reser(0)
                .date(getDate())
                .time(time)
                .dataLen((short) data.length)
                .data(data)
                .build();
        return bytePackage;
    }

    private static short getDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1, 0, 0, 0);
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        long intervalMilli = now.getTimeInMillis() - cal.getTimeInMillis();
        short xcts = (short) (intervalMilli / (24 * 60 * 60 * 1000));
        return xcts;
    }

    private static int getTime() {
        Calendar c = Calendar.getInstance();
        int val = c.get(Calendar.HOUR_OF_DAY) * 60 * 60 + c.get(Calendar.MINUTE) * 60 + c.get(Calendar.SECOND);
        return val;
    }


    public static void main(String[] args) {
        BytePackage bytePackage = PkgKit.package512(1);
        byte[] bytes = bytePackage.bytes();
        String str = ByteKit.byteArrayToHexStr(bytes);
        System.out.println(str);
        System.out.println(str.length());
    }
}
