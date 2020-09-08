package com.shenjinxiang.netty;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileKit {

    private static final Logger logger = LoggerFactory.getLogger(FileKit.class);
    private static int BYTE_BUFFER_LENGTH = 1024;

    public static void dataFromFile(String filePath) {
        File file = new File(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             FileChannel channel = fileInputStream.getChannel()){
            ByteBuffer byteBuffer = ByteBuffer.allocate(BYTE_BUFFER_LENGTH);
            StringBuilder builder = new StringBuilder();
            while (true) {
                byteBuffer.clear();
                int read = channel.read(byteBuffer);
                builder.append(new String(byteBuffer.array(), "UTF-8"));
                if (read == -1) {
                    break;
                }
            }
            logger.info("读取内容：\n" + builder.toString());

        } catch (Exception e) {
            logger.error("读取文件出错", e);
        }
    }

    public static void main(String[] args) {
        String filePath = "D:\\temp\\20200909\\0f85e9f9-bda0-4d5b-9b9f-fc1aa4ab12a5.csv";
        dataFromFile(filePath);
    }
}
