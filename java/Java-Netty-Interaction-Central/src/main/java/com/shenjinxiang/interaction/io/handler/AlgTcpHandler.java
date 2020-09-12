package com.shenjinxiang.interaction.io.handler;

import com.shenjinxiang.interaction.io.TcpHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/12 07:54
 */
public class AlgTcpHandler<T> extends TcpHandler<T> {

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
