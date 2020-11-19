package com.shenjinxiang.exam.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class FileLineWriter implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(FileLineWriter.class);

    private File file;
//    private Queue<String> lines = new LinkedList<>();
    private BlockingQueue<String> lines = new ArrayBlockingQueue<>(10);
    private boolean run;
    private boolean end;

    private String startStr;
    private String endStr;

    public FileLineWriter(File file) {
        this.file = file;
        this.run = true;
    }

    public FileLineWriter(File file, String startStr, String endStr) {
        this.file = file;
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        this.run = true;
        this.startStr = startStr;
        this.endStr = endStr;
    }

    @Override
    public void run() {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            logger.info("开始文件写入，文件：" + file.getAbsolutePath());
            if (null != this.startStr) {
                bufferedWriter.write(startStr);
            }
            while (this.run) {
                String line = lines.poll(20, TimeUnit.MILLISECONDS);
//                String line = lines.take();
                logger.info("获取到数据：" + line);
                if (null != line) {
                    bufferedWriter.write(line);
                    logger.info("写入数据：" + line);
                }

                Thread.sleep(1);
            }
            if (null != this.endStr) {
                bufferedWriter.write(endStr);
            }
            logger.info("完成文件写入，文件：" + file.getAbsolutePath());
        } catch (Exception e) {
            logger.error("文件写入出错，文件：" + file.getAbsolutePath(), e);
        } finally {
            if (null != bufferedWriter) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    logger.error("文件写入关闭输出流出错，文件：" + file.getAbsolutePath(), e);
                }
            }
            this.end = true;
        }

    }

    public void write(String line) {
//        this.lines.offer(line);
        try {
            this.lines.put(line);
        } catch (InterruptedException e) {
            logger.error("写入文件出错" +
                    "\n\t文件：" + file.getAbsolutePath() +
                    "\n\t本次内容：" + line,
                    e
            );
        }
    }

    public void close() {
        this.lines.clear();
        this.run = false;
    }

    public boolean isEnd() {
        return end;
    }
}
