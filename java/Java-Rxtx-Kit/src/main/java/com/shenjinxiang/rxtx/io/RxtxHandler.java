package com.shenjinxiang.rxtx.io;

import com.shenjinxiang.rxtx.kit.ByteKit;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RxtxHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RxtxHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel);
        logger.info("建立连接，地址：" + ctx.channel().remoteAddress() + " ID: " + ctx.channel().id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel);
        ByteBuf byteBuf = (ByteBuf) msg;
        int readBytes = byteBuf.readableBytes();
        byte[] bytes = new byte[readBytes];
        byteBuf.readBytes(bytes);

        String content = ByteKit.byteArrayToHexStr(bytes);
        logger.info("接收到信息：" + content);
    }
}
