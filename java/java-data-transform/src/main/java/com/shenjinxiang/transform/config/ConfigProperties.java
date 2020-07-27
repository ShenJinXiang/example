package com.shenjinxiang.transform.config;

import com.shenjinxiang.transform.core.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 16:08
 */
public class ConfigProperties {

    private static final Logger logger = LoggerFactory.getLogger(ConfigProperties.class);

    public final static int THREAD_POOL_SIZE;

    private static Properties properties;

    static {
        loadProperties();
        logProperties();
        THREAD_POOL_SIZE = getInt("thread-pool-size");
    }

    private static String get(String key) {
        return properties.getProperty(key);
    }

    private static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    private static boolean getBoolean(String key) {
        return Boolean.valueOf(get(key));
    }

    private static void loadProperties() {
        String path = "config.properties";
        if (Consts.isDevMode()) {
            path = "config-dev.properties";
        }
        properties = new Properties();
        InputStream inputStream = null;
        try {
            logger.info("加载配置文件：" + path);
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            properties.load(new InputStreamReader(inputStream, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null == inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void logProperties() {
        Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            Object val = properties.get(key);
            logger.info("\n\t" + key + ": " + val);
        }
    }

}
