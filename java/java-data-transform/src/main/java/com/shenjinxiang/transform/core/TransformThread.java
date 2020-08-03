package com.shenjinxiang.transform.core;

import com.shenjinxiang.transform.domain.TransformGroup;
import com.shenjinxiang.transform.domain.TransformSend;
import com.shenjinxiang.transform.handler.DataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 11:40
 */
public class TransformThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(TransformThread.class);

    private TransformGroup group;
    private byte[] data;
    private DataHandler dataHandler;

    public TransformThread(TransformGroup group, byte[] data) {
        this.group = group;
        this.data = data;
        this.dataHandler = group.getReceive().getHandler();
    }

    @Override
    public void run() {
        try {
            logger.info("进行数据转换，发送数据: " + data);
            String msg = this.dataHandler.handle(this.data);
            List<TransformSend> sends = group.getSendList();
            for (TransformSend send: sends) {
                send.getChannel().writeAndFlush(msg);
            }
        } catch (Exception e) {
            logger.error("发生错误", e);
        }
    }
}
