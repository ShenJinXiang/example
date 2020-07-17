package com.shenjinxiang.spb.task;

import com.shenjinxiang.spb.config.MyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/17 9:22
 */
@Component
public class ShellTask {

    private static final Logger logger = LoggerFactory.getLogger(ShellTask.class);

    @Autowired
    private MyConfig myConfig;

    @Scheduled(fixedRate = 100)
    public void task() {
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            Process process = Runtime.getRuntime().exec(myConfig.getPythonCommand());
            int status = process.waitFor();
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            logger.info("执行结果：" + status + " 内容：" + sb.toString());
        } catch (Exception e) {
            logger.error("执行命令出错", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error("执行命令关闭流出错", e);
                }
            }
        }
    }
}
