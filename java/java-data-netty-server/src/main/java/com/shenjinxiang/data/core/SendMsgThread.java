package com.shenjinxiang.data.core;

import com.shenjinxiang.data.kit.JsonKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/4 10:16
 */
public class SendMsgThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SendMsgThread.class);


    private List<WaveData> waveDatas = new ArrayList<>();
    private int count;

    public SendMsgThread () {
        count = 0;

        waveDatas.add(new WaveData(0));
        waveDatas.add(new WaveData(100));
        waveDatas.add(new WaveData(200));
        waveDatas.add(new WaveData(380));
    }


    @Override
    public void run() {
        while (true) {
            if (Consts.ISSENDMSG) {
                logger.info("发送数据[" + count + "]!");
                String str = createData();
                Consts.channel.writeAndFlush(str + "\n");
                count++;
                sleep(50);
            } else {
                logger.info("暂无数据请求!");
                sleep(2000);
            }
        }
    }


    private String createData() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (WaveData waveData: waveDatas) {
            list.add(waveData.createDate());
        }
        return JsonKit.toJson(list);
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.error("Sleep出错!", e);
        }
    }
}
