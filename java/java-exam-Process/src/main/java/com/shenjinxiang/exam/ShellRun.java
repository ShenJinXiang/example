package com.shenjinxiang.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/17 8:51
 */
public class ShellRun implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ShellRun.class);

    private String command;

    public ShellRun(String command) {
        logger.info("ShellRun 初始化, 命令： " + command);
        this.command = command;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            Process process = Runtime.getRuntime().exec(command);
            int status = process.waitFor();

            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
//            logger.info("执行结果：" + status + " 内容：" + sb.toString());
            logger.info("执行结果：" + sb.toString());
        } catch (Exception e) {
            logger.error("执行命令出错", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
