package com.shenjinxiang.data.core;

import com.shenjinxiang.data.kit.JsonKit;
import com.shenjinxiang.data.kit.RandomKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/4 10:16
 */
public class SendMsgThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SendMsgThread.class);

    private List<Element> elements = new ArrayList<>();

    @Override
    public void run() {
        while (true) {
//            if (Consts.ISSENDMSG) {
                logger.info("发送数据!");
                String str = createData();
                logger.info(str);
//                Consts.channel.writeAndFlush(str + "\n");
//                sleep(50);
//            } else {
//                logger.info("暂无数据请求!");
//                sleep(2000);
//            }
        }
    }

    private String createData() {
        List<Double> list = new ArrayList<>();
        for(int i = 0; i < Consts.LENGTH; i++) {
            list.add(RandomKit.randomDouble(Consts.MIN_VAL, Consts.MAX_VAL));
        }

        for (Element element : elements) {
            element.run(1);
        }
        double ran = RandomKit.randomDouble(0, 100);
        if (ran < 1) {
            elements.add(new Element());
        }

        for (Element element : elements) {
            element.replace(list);
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
