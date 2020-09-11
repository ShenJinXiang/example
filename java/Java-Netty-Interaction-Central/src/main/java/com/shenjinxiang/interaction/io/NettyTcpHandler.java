package com.shenjinxiang.interaction.io;

import com.shenjinxiang.interaction.kit.ByteKit;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/1 20:51
 */
public class NettyTcpHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyTcpHandler.class);

    private Channel channel;
    private String str;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
        logger.info("建立连接，地址：" + channel.remoteAddress() + " ID: " + channel.id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        int readBytes = byteBuf.readableBytes();
        byte[] bytes = new byte[readBytes];
        byteBuf.readBytes(bytes);
        String x = ByteKit.byteArrayToHexStr(bytes);
        System.out.println(x);

//        channel = ctx.channel();
//        String content = msg.toString();
//        logger.info("接收到内容：" + content);
//        channel.writeAndFlush(content + "\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("TCP链接，服务端出现错误", cause);
        ctx.close();
    }
}
