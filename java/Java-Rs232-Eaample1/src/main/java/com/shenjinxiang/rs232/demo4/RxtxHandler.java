package com.shenjinxiang.rs232.demo4;

import com.shenjinxiang.rs232.kit.ByteKit;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RxtxHandler extends ChannelInboundHandlerAdapter {

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
