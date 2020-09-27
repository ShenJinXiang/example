package com.shenjinxiang.rxtx.io;

import com.shenjinxiang.rxtx.kit.ByteKit;
import com.shenjinxiang.rxtx.kit.StrKit;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 22:00
 */
public class CommandReader implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private static final String SEND = "send";
    private static final String PARSE = "parse";
    private static final String CONN = "conn";
    private static final String CLOSE = "close";
    private static final String EXIT = "exit";

    private BufferedReader bufferedReader;

    public CommandReader() {
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
                    if (CONN.equalsIgnoreCase(words[0])) {
                        IOKit.runRxtx(words[1]);
                        continue;
                    }
                    if (CLOSE.equalsIgnoreCase(words[0])) {
                        IOKit.close();
                        continue;
                    }
                    if (SEND.equalsIgnoreCase(words[0])) {
                        String str = line.substring(SEND.length());
                        str = str.replace(" ", "");
                        IOKit.sendData(ByteKit.hexStrToByteArray(str));
                        continue;
                    }
                    if (PARSE.equalsIgnoreCase(words[0])) {
                        String str = line.substring(PARSE.length());
                        str = str.replace(" ", "");
                        byte[] bytes = ByteKit.hexStrToByteArray(str);
                        DataParser.parse(bytes);
                        continue;
                    }
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

    private static void info() throws Exception {
        Enumeration<NetworkInterface> nifs = null;
        nifs = NetworkInterface.getNetworkInterfaces();
        while (nifs.hasMoreElements()) {
            NetworkInterface ni = nifs.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                InetAddress addr = address.nextElement();
                if (addr instanceof Inet4Address) {
                    System.out.println("网络接口名称为：" + ni.getName());
                    System.out.println("网卡接口地址：" + addr.getHostAddress());
                    System.out.println();
                }
            }
        }
    }

}
