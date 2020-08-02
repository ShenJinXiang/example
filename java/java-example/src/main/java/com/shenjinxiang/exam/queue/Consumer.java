package com.shenjinxiang.exam.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/2 18:58
 */
public class Consumer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private int step;
    private int count;

    public Consumer(int step) {
        this.step = step;
        this.count = 0;
    }
    @Override
    public void run() {
        String str;
        while (true) {
            str = Data.poll();
            count++;
            logger.info("读取次数[" + count + "], 内容: " + str);
            logger.info("队列中数量：" + Data.size());
            try {
                Thread.sleep(this.step);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
