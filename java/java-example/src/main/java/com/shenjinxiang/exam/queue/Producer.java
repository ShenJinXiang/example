package com.shenjinxiang.exam.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/2 18:56
 */
public class Producer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private int step;

    private int count;

    public Producer(int step) {
        this.step = step;
        this.count = 0;
    }

    @Override
    public void run() {
        while (count < 100) {
            String str = "aaaaaaaaaaa" + count;
            Data.offer(str);
            logger.info("添加数据：" + str + "  队列数量：" + Data.size());
            count++;
            try {
                Thread.sleep(step);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
