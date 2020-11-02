package com.shenjinxiang.netty.io;

import com.shenjinxiang.netty.core.Config;
import com.shenjinxiang.netty.kit.ByteKit;
import com.shenjinxiang.netty.kit.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 22:00
 */
public class CommandReader implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private static final String EXIT = "exit";
    private static final String SEND = "send";
    private static final String INFO = "info";
    private static final String LS = "ls";

    private BufferedReader bufferedReader;

    public CommandReader() throws UnsupportedEncodingException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    @Override
    public void run() {
        while (true) {
            logger.info("请输入命令: ");
            try {
                String line = bufferedReader.readLine();
                if (StrKit.notBlank(line) && !"".equals(line.trim())) {
                    logger.info("录入内容：" + line);
                    String[] words = line.trim().split("\\s+");
                    if (SEND.equalsIgnoreCase(words[0])) {
                        byte[] bytes = null;
                        try {
                            String content = words[1];
                            bytes = ByteKit.hexStrToByteArray(content);
                        } catch (Exception e) {
                            logger.error("获取发送内容出错！", e);
                        }
                        if (null != bytes) {
                            IOKit.send(bytes);
                        }
                        continue;
                    }

                    if (INFO.equalsIgnoreCase(words[0])) {
                        Config.log();
                        continue;
                    }

                    if (LS.equalsIgnoreCase(words[0])) {
                        ls();
                        continue;
                    }

                    // 程序结束
                    if (EXIT.equalsIgnoreCase(words[0])) {
                        logger.info("程序结束！");
                        System.exit(0);
                    }
                    logger.info("没有任务!");
                }
            } catch (Exception e) {
                logger.error("处理任务出错", e);
            }

        }
    }

    private static void ls() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("网口列表：");
        Enumeration<NetworkInterface> nifs = null;
        nifs = NetworkInterface.getNetworkInterfaces();
        while (nifs.hasMoreElements()) {
            NetworkInterface ni = nifs.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                InetAddress addr = address.nextElement();
                if (addr instanceof Inet4Address) {
                    stringBuilder.append("\n\t网口名称：").append(ni.getName())
                            .append("\t网口地址：").append(addr.getHostAddress());
                }
            }
        }
        logger.info(stringBuilder.toString());
    }
}
