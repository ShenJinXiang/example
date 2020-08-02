package com.shenjinxiang.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/2 17:58
 */
public class FileInTest {

    private static final Logger logger = LoggerFactory.getLogger(FileInTest.class);

    public void run() throws FileNotFoundException {
        File file = new File("/Users/shenjinxiang/shenjinxiang/git_xm/temp/test1.data");
        FileIn fileIn = new FileIn(file);
        String str;
        int count = 0;
        while ((str = fileIn.read()) != null) {
            logger.info(String.valueOf(count));
            logger.info(str);
            count++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fileIn.close();
    }
}
