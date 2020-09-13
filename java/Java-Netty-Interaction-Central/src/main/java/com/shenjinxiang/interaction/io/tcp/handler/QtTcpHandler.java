package com.shenjinxiang.interaction.io.tcp.handler;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.io.IOKit;
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
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端断开连接，地址[" + ctx.channel().remoteAddress() + "] ID: " + ctx.channel().id());
        this.conn = false;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        channel = ctx.channel();
        String content = msg.toString();
        logger.info("接收到内容：" + content);
        if ("start".equalsIgnoreCase(content)) {
            Config.SENDER.start();
            IOKit.setQtStart(true);
        }
        if ("end".equalsIgnoreCase(content)) {
            Config.SENDER.end();
            IOKit.setQtStart(false);
        }
    }

    @Override
    public void sendMsg(T msg) {
        if (conn) {
            this.channel.writeAndFlush(msg + "\n");
        }
    }
}
