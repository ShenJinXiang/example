package com.shenjinxiang.exam;

import com.shenjinxiang.exam.core.Consts;
import com.shenjinxiang.exam.io.FileLineWriter;
import com.shenjinxiang.exam.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.shenjinxiang.exam.core.Consts.FILE_LINE_WRITER;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Java-File-Thread-Kit Start...");

        String filePath = "D:\\temp\\20201119\\w.json";
        FILE_LINE_WRITER = new FileLineWriter(new File(filePath), "[", "]");
        ThreadPool.getThread().execute(FILE_LINE_WRITER);
        ThreadPool.getThread().execute(new Sender(100));
    }
}
