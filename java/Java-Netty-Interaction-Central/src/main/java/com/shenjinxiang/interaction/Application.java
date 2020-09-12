package com.shenjinxiang.interaction;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.entity.config.CentralConfig;
import com.shenjinxiang.interaction.entity.config.Target;
import com.shenjinxiang.interaction.io.IOKit;
import com.shenjinxiang.interaction.kit.JsonKit;
import com.shenjinxiang.interaction.kit.PathKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName Application
 * @Author ShenjinXiang
 * @Date 2020/9/10 22:36
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        logger.info("Central Start...");
        initConfig();
        Config.CENTRAL_CONFIG.log();
//        IOKit.runQtServer();
        IOKit.runPointUdpServer();
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
            Config.CENTRAL_CONFIG = JsonKit.fromJson(content, CentralConfig.class);
            if (null == Config.CENTRAL_CONFIG) {
                logger.error("未读取到解析config.json配置内容");
            }
            for (Target target : Config.CENTRAL_CONFIG.getTargets()) {
                target.setAddress(new InetSocketAddress(target.getUdpHost(), target.getUdpPort()));
            }
        } catch (Exception e) {
            logger.error("解析 config.json 配置出错", e);
            throw e;
        }
    }
}
