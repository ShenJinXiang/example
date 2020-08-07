package com.shenjinxiang.client.io;

import com.shenjinxiang.client.core.Config;
import com.shenjinxiang.client.kit.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 22:00
 */
public class CommandReader implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private static final String SEND_PREFIX = "send ";

    private BufferedReader bufferedReader;

    public CommandReader() throws UnsupportedEncodingException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in, Config.ENCODE));
    }

    @Override
    public void run() {
        logger.info("请输入命令");
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (StrKit.notBlank(line)) {
                    if (line.startsWith(SEND_PREFIX)) {
                        // 向服务端发送消息
                        Config.CLIENT_CHANNEL.writeAndFlush(line.substring(SEND_PREFIX.length()) + "\n");
                        continue;
                    }
                    if ("exit".equalsIgnoreCase(line)) {
                        logger.info("程序结束！");
                        Config.CLIENT_CONTEXT.close();
                        System.exit(0);
                    }
                }
            } catch (Exception e) {

            }

        }
    }
}
