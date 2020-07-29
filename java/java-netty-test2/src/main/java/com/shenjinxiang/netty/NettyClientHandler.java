package com.shenjinxiang.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/29 17:39
 */
public class NettyClientHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    private Channel channel;

    public void sendMessage(String str) {

    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) {
        channel = channelHandlerContext.channel();
//        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks!", CharsetUtil.UTF_8));
        logger.info("建立连接");
    }

    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception{
//        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
        String msg = o.toString();
        logger.info("读取数据：" + msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
        cause.printStackTrace();
        logger.error("出错了", e);
        channelHandlerContext.close();
    }

}
