package com.shenjinxiang.exam.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/2 11:21
 */
public class FileIn {

    private static final Logger logger = LoggerFactory.getLogger(FileIn.class);

    private BufferedReader bufferedReader;

    public FileIn(File file) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        logger.info("建立输入流");
    }

    public String read() {
        try {
            String line = bufferedReader.readLine();
            return line;
        } catch (IOException e) {
            logger.error("读取内容出错", e);
            return null;
        }
    }

    public void close() {
        if (null != bufferedReader) {
            try {
                bufferedReader.close();
                logger.info("输入流关闭");
            } catch (IOException e) {
                logger.error("关闭流出错", e);
            }
        }
    }

}
