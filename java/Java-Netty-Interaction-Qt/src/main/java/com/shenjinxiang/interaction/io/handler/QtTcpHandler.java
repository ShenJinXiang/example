package com.shenjinxiang.interaction.io.handler;

import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/12 07:05
 */
public class QtTcpHandler<T> extends TcpHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(QtTcpHandler.class);

    @Override
    public void sendMsg(T msg) {
        if (conn) {
            this.channel.writeAndFlush(msg + "\n");
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        channel = ctx.channel();
        String content = msg.toString();
        logger.info("接收到内容：" + content);
    }
}
