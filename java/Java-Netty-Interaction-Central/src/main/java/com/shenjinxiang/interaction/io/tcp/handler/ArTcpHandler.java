package com.shenjinxiang.interaction.io.tcp.handler;

import io.netty.channel.ChannelHandlerContext;

/**
 * @ClassName ArTcpServerHandler
 * @Author ShenjinXiang
 * @Date 2020/9/11 23:02
 */
public class ArTcpHandler<T> extends TcpHandler<T> {

    /**
     * 发送消息
     * @param msg
     */
    @Override
    public void sendMsg(T msg) {

    }

    /**
     * 接收消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    }
}
