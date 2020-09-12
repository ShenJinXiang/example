package com.shenjinxiang.interaction;

import com.shenjinxiang.interaction.kit.ByteKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName Application
 * @Author ShenjinXiang
 * @Date 2020/9/10 22:36
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Central Start...");
//        ThreadPool.getThread().execute(new NettyTcpServer(9999));
        byte[] bytes1 = "\n".getBytes();
        byte[] bytes2 = "\n".getBytes(StandardCharsets.UTF_8);
        byte[] bytes3 = "\n".getBytes(StandardCharsets.ISO_8859_1);
        byte[] bytes4 = "\n".getBytes(StandardCharsets.US_ASCII);
        System.out.println(ByteKit.byteArrayToHexStr(bytes1));
        System.out.println(ByteKit.byteArrayToHexStr(bytes2));
        System.out.println(ByteKit.byteArrayToHexStr(bytes3));
        System.out.println(ByteKit.byteArrayToHexStr(bytes4));
    }
}
