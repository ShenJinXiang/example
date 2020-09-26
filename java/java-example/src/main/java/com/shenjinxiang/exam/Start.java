package com.shenjinxiang.exam;

import com.shenjinxiang.exam.jsoup.JsoupTest;
import com.shenjinxiang.exam.jsoup.domain.Student;
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
        String str = "AA02466B202010D0FFFF1D4D07B24E55E0";
        byte[] bytes = ByteKit.hexStrToByteArray(str);
        System.out.println(toHex(bytes, 0, bytes.length));
        String str1 = new String(bytes);
        System.out.println(str1);
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
