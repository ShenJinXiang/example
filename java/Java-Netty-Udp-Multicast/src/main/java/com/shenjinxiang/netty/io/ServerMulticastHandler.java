package com.shenjinxiang.netty.io;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class ServerMulticastHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger logger = LoggerFactory.getLogger(ServerMulticastHandler.class);

    private InetSocketAddress groupAddress;

    private int count = 0;

    public ServerMulticastHandler(InetSocketAddress groupAddress) {
        this.groupAddress = groupAddress;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("建立连接: " + ctx.channel().remoteAddress());
        while (true) {
            try {
                logger.info("server 发送数据，次数：" + this.count);
                String str = "Server 端 正在 发送数据，当前次数：" + this.count;
                ctx.channel().writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(str.getBytes("UTF-8")), this.groupAddress));
                this.count++;
                Thread.sleep(1000);
            }catch (Exception e) {
                logger.error("eeerror");
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        ByteBuf buf = datagramPacket.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String content = new String(req);
        logger.info("接收到内容：" + content);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
