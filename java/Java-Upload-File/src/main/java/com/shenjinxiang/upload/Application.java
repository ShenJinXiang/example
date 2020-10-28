package com.shenjinxiang.upload;

import com.shenjinxiang.upload.io.CommandReader;
import com.shenjinxiang.upload.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/10/28 20:02
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Java-Upload-File Start...");
        ThreadPool.getThread().execute(new CommandReader());
    }
}
