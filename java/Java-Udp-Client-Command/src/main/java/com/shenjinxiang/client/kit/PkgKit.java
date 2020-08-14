package com.shenjinxiang.client.kit;

import com.shenjinxiang.client.domain.Data514;

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
                .mId((short) 15)
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

    public static BytePackage package514(int xh) {
        int time = getTime();
        Data514 data514 = new Data514();
        data514.setT(time);
        data514.setX((float)14.23);
        data514.setY((float)14.23);
        data514.setZ((float)14.23);
        data514.setVx((float) 10.123);
        data514.setVy((float) 10.123);
        data514.setVz((float) 10.123);
        byte[] data = data514.data();
        BytePackage bytePackage = BytePackage.builder()
                .mId((short) 514)
                .sId(1235)
                .dId(1235)
                .bId(1235)
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
        BytePackage bytePackage512 = PkgKit.package512(1);
        byte[] bytes512 = bytePackage512.bytes();
        String str512 = ByteKit.byteArrayToHexStr(bytes512);
        System.out.println(str512);
        System.out.println(str512.length());

        BytePackage bytePackage513 = PkgKit.package513(2);
        byte[] bytes513 = bytePackage513.bytes();
        String str513 = ByteKit.byteArrayToHexStr(bytes513);
        System.out.println(str513);
        System.out.println(str513.length());

        BytePackage bytePackage514 = PkgKit.package514(3);
        byte[] bytes514 = bytePackage514.bytes();
        String str514 = ByteKit.byteArrayToHexStr(bytes514);
        System.out.println(str514);
        System.out.println(str514.length());
    }
}
