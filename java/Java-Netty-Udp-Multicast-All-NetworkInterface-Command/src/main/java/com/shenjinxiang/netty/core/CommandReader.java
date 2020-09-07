package com.shenjinxiang.netty.core;

import com.shenjinxiang.netty.kit.StrKit;
import com.shenjinxiang.netty.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 22:00
 */
public class CommandReader implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private static final String SEND_PREFIX = "send";
    private static final String EXIT = "exit";
    private static final String CLOSE = "close";
    private static final String CONN = "conn";

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
                            for (NettyMulticastHandler handler: Config.HANDLER_LIST) {
                                handler.sendMsg(msg);
                            }
                        } else {
                            logger.info("发送内容不能为空!");
                        }
                        continue;
                    }
                    if (EXIT.equalsIgnoreCase(words[0])) {
                        logger.info("程序结束！");
                        System.exit(0);
                    }
                    if (CONN.equalsIgnoreCase(words[0])) {
                        InetSocketAddress groupAddress = new InetSocketAddress(Config.HOST, Config.PORT);
                        for (LocalAddress localAddress: Config.LOCAL_ADDRESS_LIST) {
                            ThreadPool.getThread().execute(new NettyMulticastUdp(groupAddress, localAddress));
                        }
                        Thread.sleep(5000);
                    }
                    if (CLOSE.equalsIgnoreCase(words[0])) {
                        for (NettyMulticastHandler handler: Config.HANDLER_LIST) {
                            handler.close();
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("处理任务出错", e);
            }

        }
    }


}
