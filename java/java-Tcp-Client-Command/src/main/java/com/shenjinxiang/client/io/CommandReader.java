package com.shenjinxiang.client.io;

import com.shenjinxiang.client.core.Config;
import com.shenjinxiang.client.domain.ControTcpCommand;
import com.shenjinxiang.client.kit.JsonKit;
import com.shenjinxiang.client.kit.StrKit;
import com.shenjinxiang.client.kit.ThreadPool;
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
                        String command = line.substring(SEND_PREFIX.length());
                        String token = UUID.randomUUID().toString().replace("-", "");
                        ControTcpCommand controTcpCommand = new ControTcpCommand();
                        controTcpCommand.setCommand(command);
                        controTcpCommand.setToken(token);
                        // 向服务端发送消息
                        Config.CLIENT_CHANNEL.writeAndFlush(JsonKit.toJson(controTcpCommand) + "\n");
                        continue;
                    }
                    if ("conn".equalsIgnoreCase(line)) {
                        ThreadPool.getThread().execute(new NettyTcpClient(Config.SERVER_IP, Config.SERVER_PORT));
                    }
                    if ("close".equalsIgnoreCase(line)) {
                        Config.CLIENT_CONTEXT.close();
                        logger.info("关闭连接");
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
