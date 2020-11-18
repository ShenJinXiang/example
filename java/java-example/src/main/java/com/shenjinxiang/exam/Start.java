package com.shenjinxiang.exam;

import com.shenjinxiang.exam.jsoup.JsoupTest;
import com.shenjinxiang.exam.jsoup.domain.Student;
import com.shenjinxiang.exam.kit.ByteArrayConveter;
import com.shenjinxiang.exam.kit.NettyTcpClient;
import com.shenjinxiang.kit.ByteKit;
import com.shenjinxiang.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/2 10:59
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) throws Exception {
//        logger.info("start...");
//        ThreadPool.getThread().execute(new NettyTcpClient("localhost", 9999, "clien1"));
//        ThreadPool.getThread().execute(new NettyTcpClient("localhost", 9999, "clien2"));
//        ThreadPool.getThread().execute(new NettyTcpClient("localhost", 9999, "clien3"));
//        String str = "AA02466B202010D0FFFF1D4D07B24E55E0";
//        byte[] bytes = ByteKit.hexStrToByteArray(str);
//        System.out.println(toHex(bytes, 0, bytes.length));
//        String str1 = new String(bytes);
//        System.out.println(str1);

//        String ip = "94.99.21.52";
        String ip = "232.77.4.15";
        String[] strs = ip.split("\\.");
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            byte[] bs = ByteArrayConveter.getByteArray(Integer.parseInt(strs[i]));
            bytes[i] = bs[3];
        }
        System.out.println(ByteKit.byteArrayToHexStr(bytes));

//        String str = "01005e4d040f9094e45f542208004500005756d40000801183ce5e631534e84d040f400060080043b8fb" ;
////                "801111000c02650011112501051000602200000000000000b01dcaabd6141b00c8abd614c0dd6642e5dcbe4b0a8009e95680a4080200000000ffff";
//        byte[] bytes1 = ByteKit.hexStrToByteArray(str);
//        System.out.println(bytes1.length);
        int i1 = 4369;
        byte[] b1 = ByteArrayConveter.getByteArray(i1);
        System.out.println(b1);

        int i2 = 33742;
        byte[] b2 = ByteArrayConveter.getByteArray(i1);
        System.out.println(b2);
        System.out.println(ByteKit.byteArrayToHexStr(b2));

    }

    public static final String toHex(byte[] data, int off, int length) {
        // double size, two bytes (hex range) for one byte
        StringBuffer buf = new StringBuffer(data.length * 2);
        for (int i = off; i < length; i++) {
            // don't forget the second hex digit
            if (((int) data[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) data[i] & 0xff, 16));
            if (i < data.length - 1) {
                buf.append(" ");
            }
        }
        return buf.toString();
    }
}
