package com.shenjinxiang.netty.core;

import com.shenjinxiang.netty.kit.PathKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/10/17 18:01
 */
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    public static boolean LOADED = false;
    public static String NETWOKINTERFACE;
    public static String MULTICAST_HOST;
    public static int MULTICAST_PORT;

    public static void log() {
        StringBuilder stringBuilder = new StringBuilder("当前配置信息：");
        stringBuilder.append("\n\t网口").append(NETWOKINTERFACE)
                .append("\n\t组播地址").append(MULTICAST_HOST)
                .append("\n\t组播端口：").append(MULTICAST_PORT);
        logger.info(stringBuilder.toString());
    }

    public static void loadConfig() {
        String fileName = "config.properties";
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            if (PathKit.isJar()) {
                File file = new File(PathKit.getCurrentPath(), fileName);
                inputStream = file.exists() ? new FileInputStream(file) : null;
            }
            if (null == inputStream) {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            }
            properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            NETWOKINTERFACE = properties.getProperty("netwokinterface");
            MULTICAST_HOST = properties.getProperty("multicastHost");
            MULTICAST_PORT = Integer.parseInt(properties.getProperty("multicastPort"));
            logger.info("配置加载完成！");
            log();
        } catch (Exception e) {
            LOADED = false;
            logger.error("加载配置文件出错！", e);
        }
    }
}
