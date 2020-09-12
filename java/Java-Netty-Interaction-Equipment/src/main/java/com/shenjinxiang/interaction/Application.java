package com.shenjinxiang.interaction;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.core.Sender;
import com.shenjinxiang.interaction.entity.config.EquipmentConfig;
import com.shenjinxiang.interaction.kit.JsonKit;
import com.shenjinxiang.interaction.kit.PathKit;
import com.shenjinxiang.interaction.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        logger.info("Java-Netty-Interaction-Equipment start...");
        initConfig();
        ThreadPool.getThread().execute(new Sender());
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
            String content = new String(bytes, StandardCharsets.UTF_8);
            Config.EQUIPMENT_CONFIG = JsonKit.fromJson(content, EquipmentConfig.class);
            if (null == Config.EQUIPMENT_CONFIG) {
                logger.error("未读取到解析config.json配置内容");
            }
            Config.EQUIPMENT_CONFIG.log();
        } catch (Exception e) {
            logger.error("解析 config.json 配置出错", e);
            throw e;
        }
    }
}
