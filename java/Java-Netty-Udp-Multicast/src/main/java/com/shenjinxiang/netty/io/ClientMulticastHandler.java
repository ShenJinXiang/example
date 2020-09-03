package com.shenjinxiang.netty.io;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class ClientMulticastHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger logger = LoggerFactory.getLogger(ServerMulticastHandler.class);
    private InetSocketAddress groupAddress;

    public ClientMulticastHandler(InetSocketAddress groupAddress) {
        this.groupAddress = groupAddress;
    }

    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("建立连接: " + ctx.channel().remoteAddress());
        while (this.count < 10) {
            try {
                String str = "Client 端 正在发送数据，次数: " + this.count;
                logger.info("client发送数据" + this.count);
                ctx.channel().writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(str.getBytes("UTF-8")), this.groupAddress));
                this.count++;
                Thread.sleep(5000);
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
        logger.info("client接收到内容：" + content);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
