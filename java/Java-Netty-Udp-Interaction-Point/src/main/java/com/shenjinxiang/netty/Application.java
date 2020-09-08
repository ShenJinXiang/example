package com.shenjinxiang.netty;

import com.shenjinxiang.netty.core.Config;
import com.shenjinxiang.netty.entity.PointConfig;
import com.shenjinxiang.netty.io.CommandReader;
import com.shenjinxiang.netty.kit.JsonKit;
import com.shenjinxiang.netty.kit.PathKit;
import com.shenjinxiang.netty.kit.ThreadPool;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        logger.info("start...");
        initConfig();
        Config.POINT_CONFIG.log();
        ThreadPool.getThread().execute(Config.SENDER);
        ThreadPool.getThread().execute(new CommandReader());
    }

    private static void initConfig() throws Exception {
        String fileName = "config.json";
        InputStream inputStream = null;
        try {
            if (PathKit.isJar()) {
                File file = new File(PathKit.getCurrentPath(), fileName);
                inputStream = file.exists() ? new FileInputStream(file) : null;
            }
            if (null == inputStream) {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] buff = new byte[1024 * 1024];
            while ((len = inputStream.read(buff)) != -1) {
                byteArrayOutputStream.write(buff, 0, len);
            }
            byteArrayOutputStream.flush();

            byte[] bytes = byteArrayOutputStream.toByteArray();
            String content = new String(bytes, Config.ENCODE);
            Config.POINT_CONFIG = JsonKit.fromJson(content, PointConfig.class);
            if (null == Config.POINT_CONFIG) {
                logger.error("未读取到解析config.json配置内容");
            }
        } catch (Exception e) {
            logger.error("解析 config.json 配置出错", e);
            throw e;
        }
    }
}
