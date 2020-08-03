package com.shenjinxiang.transform.config;

import com.shenjinxiang.transform.core.Consts;
import com.shenjinxiang.transform.domain.TransformGroup;
import com.shenjinxiang.transform.domain.TransformReceive;
import com.shenjinxiang.transform.domain.TransformSend;
import com.shenjinxiang.transform.kit.JsonKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 16:24
 */
public class TransformConfig {

    private static final Logger logger = LoggerFactory.getLogger(TransformConfig.class);

    public static List<TransformGroup> groupList;

    public static void init() throws Exception {
        String fileName = "transform.json";
        InputStream inputStream = null;
        try {
            if (Consts.isJar()) {
                File file = new File(Consts.getCurrentPath(), fileName);
                inputStream = file.exists() ? new FileInputStream(file) : null;
            }
            if (null == inputStream) {
                if(Consts.isDevMode()) {
                    fileName = "transform-dev.json";
                }
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
            String content = new String(bytes, Consts.ENCODE);
//            groupList = JsonKit.fromJson(content, new TypeReference<List<TransformGroup>>() {});
            groupList = JsonKit.fromJsonAsList(content, TransformGroup.class);
            if (null != groupList && groupList.size() > 0) {
                logGroupList();
            } else {
                logger.error("未读取到解析transform.json配置内容");
            }

        } catch (Exception e) {
            logger.error("解析transform.json配置出错", e);
            throw e;
        }
    }

    private static void logGroupList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("解析数据转换配置：\n");
        for (int index = 0; index < groupList.size(); index++) {
            TransformGroup group = groupList.get(index);
            stringBuilder.append("\t第").append(index + 1).append("组配置:");
            boolean enable = group.isEnable();
            stringBuilder.append("\n\t\t enable: ").append(enable);
            TransformReceive receive = group.getReceive();
            stringBuilder.append("\n\t\t receive: ");
            stringBuilder.append("\n\t\t\t type: ").append(receive.getType());
//            stringBuilder.append("\n\t\t\t ip: ").append(receive.getIp());
            stringBuilder.append("\n\t\t\t port: ").append(receive.getPort());
            stringBuilder.append("\n\t\t\t handler: ").append(receive.getDataHandler());
            stringBuilder.append("\n\t\t\t handler: ").append(receive.getHandler());
            List<TransformSend> send = group.getSendList();
            stringBuilder.append("\n\t\t send: ");
            for (int jndex = 0; jndex < send.size(); jndex++) {
                TransformSend s = send.get(jndex);
                stringBuilder.append("\n\t\t\t 第").append(jndex + 1).append("组send");
                stringBuilder.append("\n\t\t\t\t type: " + s.getType());
                stringBuilder.append("\n\t\t\t\t ip: " + s.getIp());
                stringBuilder.append("\n\t\t\t\t port: " + s.getPort());
            }
            stringBuilder.append("\n\n");
        }
        logger.info(stringBuilder.toString());
    }

}
