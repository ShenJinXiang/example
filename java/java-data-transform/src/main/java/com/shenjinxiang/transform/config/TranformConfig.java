package com.shenjinxiang.transform.config;

import com.alibaba.fastjson.JSONArray;
import com.shenjinxiang.transform.core.Consts;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 16:24
 */
public class TranformConfig {

    private static final Logger logger = LoggerFactory.getLogger(TranformConfig.class);

    private static final List<Map<String, Object>> list = new ArrayList<>();

    static {
        loadTransform();
    }

    private static void loadTransform() {
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
            ByteOutputStream byteOutputStream = new ByteOutputStream();
            int len = 0;
            byte[] buff = new byte[1024 * 1024];
            while ((len = inputStream.read(buff)) != -1) {
                byteOutputStream.write(buff, 0, len);
            }
            byteOutputStream.flush();

            byte[] bytes = byteOutputStream.getBytes();
            String str = new String(bytes, Consts.ENCODE);
            JSONArray jsonArray = (JSONArray) JSONArray.parse(str);
            for(Object object: jsonArray) {

            }

        } catch (Exception e) {
            logger.error("", e);
        }




    }

    private static InputStream getInputStream(File file) throws Exception {
        return new FileInputStream(file);
    }

}
