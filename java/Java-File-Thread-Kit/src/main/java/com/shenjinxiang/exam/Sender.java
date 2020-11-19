package com.shenjinxiang.exam;

import com.shenjinxiang.exam.core.Consts;
import com.shenjinxiang.exam.entity.Data;
import com.shenjinxiang.exam.kit.JsonKit;
import com.shenjinxiang.exam.kit.StrKit;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.scope.ConstructorScope;

import static com.shenjinxiang.exam.core.Consts.FILE_LINE_WRITER;

public class Sender implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    private int count;
    private int num;

    public Sender(int num) {
        this.count = 0;
        this.num = num;
    }


    @Override
    public void run() {
        while (this.count < this.num) {
            try {
                Data data = new Data();
                data.setCount(count);
                data.setName("刘备");
                data.setName(StrKit.randomDigit(4));
                String line = JsonKit.toJson(data) + ",\n";
                logger.info("发送：" + line);
                FILE_LINE_WRITER.write(line);
                this.count++;

                Thread.sleep(20);
            } catch (Exception e) {
                logger.error("sender出错", e);
            }
        }
        logger.info("停止发送！");
        FILE_LINE_WRITER.close();
        logger.info("Sender end...");
    }
}
