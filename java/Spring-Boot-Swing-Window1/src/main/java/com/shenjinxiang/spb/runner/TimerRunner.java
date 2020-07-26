package com.shenjinxiang.spb.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/26 21:35
 */
@Component
public class TimerRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(TimerRunner.class);

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            logger.info("TimerRunner ...");
            Thread.sleep(100);

        }
    }
}
