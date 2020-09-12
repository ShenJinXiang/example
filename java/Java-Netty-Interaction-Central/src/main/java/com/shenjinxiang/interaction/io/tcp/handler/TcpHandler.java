package com.shenjinxiang.interaction.io.tcp.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/18 21:42
 */
@ChannelHandler.Sharable
public abstract class TcpHandler<T> extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TcpHandler.class);

    protected ChannelHandlerContext context;
    protected Channel channel;
    protected boolean conn = false;

    public abstract void sendMsg(T msg);

    public void close() {
        if (conn) {
            this.context.close();
            logger.info("关闭TCP链接，地址[" + this.channel.remoteAddress() + "] ID: " + this.channel.id());
            conn = false;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.context = ctx;
        this.channel = ctx.channel();
        this.conn = true;
        logger.info("建立连接，地址：" + this.channel.remoteAddress() + " ID: " + this.channel.id());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("TCP链接，服务端出现错误", cause);
    }

    public boolean isConn() {
        return conn;
    }

    public void setConn(boolean conn) {
        this.conn = conn;
    }
}
