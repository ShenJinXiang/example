package com.shenjinxiang.exam;

import com.shenjinxiang.exam.jsoup.JsoupTest;
import com.shenjinxiang.exam.jsoup.domain.Student;
import com.shenjinxiang.exam.kit.NettyTcpClient;
import com.shenjinxiang.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/2 10:59
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) throws Exception {
        logger.info("start...");
        ThreadPool.getThread().execute(new NettyTcpClient("localhost", 9999, "clien1"));
        ThreadPool.getThread().execute(new NettyTcpClient("localhost", 9999, "clien2"));
        ThreadPool.getThread().execute(new NettyTcpClient("localhost", 9999, "clien3"));
    }
}
