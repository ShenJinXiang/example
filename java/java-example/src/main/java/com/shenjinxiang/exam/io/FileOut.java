package com.shenjinxiang.exam.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/2 11:27
 */
public class FileOut {

    private static final Logger logger = LoggerFactory.getLogger(FileOut.class);

    private BufferedWriter writer;

    public FileOut(File file) throws FileNotFoundException {
        logger.info("建立写入流，文件: " + file.getAbsolutePath());
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
    }

    public boolean write(String str) {
        try {
            writer.write(str);
//            logger.info("写入内容: " + str);
            return true;
        } catch (Exception e) {
            logger.error("写入内容出错，内容:" + str, e);
            return false;
        }
    }
    public void close() {
        if (null != writer) {
            try {
                writer.flush();
            } catch (IOException e) {
                logger.error("flush出错", e);
            } finally {
                try {
                    writer.close();
                    logger.info("关闭链接");
                } catch (IOException e) {
                    logger.error("关闭链接出错", e);
                }
            }
        }
    }
}
