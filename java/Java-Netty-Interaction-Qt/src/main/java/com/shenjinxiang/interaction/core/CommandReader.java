package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.io.IOKit;
import com.shenjinxiang.interaction.kit.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 22:00
 */
public class CommandReader implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private static final String START = "start";
    private static final String END = "end";
    private static final String EXIT = "exit";
    private static final String CLOSE = "close";
    private static final String CONN = "conn";

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
                        IOKit.runTcpClient();
                        Thread.sleep(3000);
                        continue;
                    }
                    if (CLOSE.equalsIgnoreCase(words[0])) {
                        IOKit.closeTcpClient();;
                        continue;
                    }
                    if (START.equalsIgnoreCase(words[0])) {
                        IOKit.sendCentralMsg("start");
                        continue;
                    }
                    if (END.equalsIgnoreCase(words[0])) {
                        IOKit.sendCentralMsg("end");
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

}
