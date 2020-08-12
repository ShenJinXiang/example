package com.shenjinxiang.client.core;

import com.shenjinxiang.client.kit.StrKit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 22:00
 */
public class CommandReader implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private static final String PKG_PREFIX = "pkg";
    private static final String SEND_PREFIX = "send";
    private static final String SET_PREFIX = "set";
    private static final String INFO = "info";
    private static final String EXIT = "exit";

    private BufferedReader bufferedReader;

    public CommandReader() throws UnsupportedEncodingException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in, Config.ENCODE));
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
                    if (SEND_PREFIX.equalsIgnoreCase(words[0])) {
                        String msg = line.substring(line.indexOf(words[1]));
                        if (StrKit.notBlank(msg)) {
                            logger.info("发送数据: " + msg);
                            UdpKit.sendMsg(msg, Config.SERVER_IP, Config.SERVER_PORT);
                            logger.info("处理完毕!");
                        } else {
                            logger.info("发送内容不能为空!");
                        }
                        continue;
                    }
                    if (SET_PREFIX.equalsIgnoreCase(words[0])) {
                        if (words.length != 3) {
                            logger.info("设置格式错误，请重新输入");
                            continue;
                        }
                        if ("server-host".equalsIgnoreCase(words[1])) {
                            Config.SERVER_IP = words[2];
                            Config.logInfo();
                            logger.info("处理完毕!");
                        } else if ("server-port".equalsIgnoreCase(words[1])) {
                            Config.SERVER_PORT = Integer.parseInt(words[2]);
                            Config.logInfo();
                            logger.info("处理完毕!");
                        } else {
                            logger.info("无此参数，请重新输入");
                        }
                        continue;
                    }
                    if (PKG_PREFIX.equalsIgnoreCase(words[0])) {

                        continue;
                    }
                    if (INFO.equalsIgnoreCase(words[0])) {
                        Config.logInfo();
                        continue;
                    }
                    if (EXIT.equalsIgnoreCase(words[0])) {
                        logger.info("程序结束！");
                        UdpKit.CHANNEL.close();
                        System.exit(0);
                    }
                    logger.info("没有任务!");
                }
            } catch (Exception e) {
                logger.error("处理任务出错", e);
            }

        }
    }

}
