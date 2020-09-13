package com.shenjinxiang.exam.netty;

import com.shenjinxiang.exam.kit.ByteKit;
import com.shenjinxiang.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {

    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws InterruptedException {
        NettyTcpClientHandler handler = new NettyTcpClientHandler();
        ThreadPool.getThread().execute(new NettyTcpClient("127.0.0.1", 6000, handler));
//        Thread.sleep(3000);

//        logger.info("conn: " + handler.isConn());
//        logger.info("发送数据");
//        while (true) {
//            handler.sendMsg(ByteKit.hexStrToByteArray("00000001000001380103020403010402000300000000000000000000000000000000"));
//            Thread.sleep(1000);
//        }
    }
}
