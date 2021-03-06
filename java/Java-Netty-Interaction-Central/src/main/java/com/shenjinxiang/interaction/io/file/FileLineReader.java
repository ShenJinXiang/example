package com.shenjinxiang.interaction.io.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FileLineReader implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(FileLineReader.class);

    private File file;
    private BlockingQueue<String> lines = new ArrayBlockingQueue<>(10);
    private boolean end = false;
    private BufferedReader reader = null;

    public FileLineReader(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file), StandardCharsets.UTF_8));
            String line;
            while (!end && null != (line = reader.readLine())) {
                this.lines.put(line);
            }
            logger.info("文件读取完成！");
        } catch (Exception e) {
            logger.error("读取文件出错", e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("关闭文件流出错", e);
                }
            }
        }

    }

    public String readLine() {
        return this.lines.poll();
    }

    public void close() {
        this.lines.clear();
//        System.out.println("清理缓冲区大小: " + lines.size());
        this.end = true;
    }

}
