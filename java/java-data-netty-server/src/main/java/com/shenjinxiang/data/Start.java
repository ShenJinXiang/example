package com.shenjinxiang.data;

import com.shenjinxiang.data.core.NettyTcpServer;
import com.shenjinxiang.data.core.SendMsgThread;
import com.shenjinxiang.data.core.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/4 10:04
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {
        ThreadPool.getThread().execute(new NettyTcpServer(6001));
        ThreadPool.getThread().execute(new SendMsgThread());
    }
}
