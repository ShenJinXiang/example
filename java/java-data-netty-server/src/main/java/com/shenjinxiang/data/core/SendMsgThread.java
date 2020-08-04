package com.shenjinxiang.data.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/4 10:16
 */
public class SendMsgThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SendMsgThread.class);

    @Override
    public void run() {
        while (true) {
            if (Consts.ISSENDMSG) {
                logger.info("发送数据!");
                Consts.channel.writeAndFlush("发送数据发送数据发送数据发送数据发送数据发送数据\n");
                sleep(50);
            } else {
                logger.info("暂无数据请求!");
                sleep(2000);
            }
        }
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.error("Sleep出错!", e);
        }
    }
}
