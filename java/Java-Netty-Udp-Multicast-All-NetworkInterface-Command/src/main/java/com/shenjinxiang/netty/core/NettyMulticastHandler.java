package com.shenjinxiang.netty.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class NettyMulticastHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger logger = LoggerFactory.getLogger(NettyMulticastHandler.class);

    private InetSocketAddress groupAddress;
    private ChannelHandlerContext context;
    private LocalAddress localAddress;
    private boolean conn = false;

    public NettyMulticastHandler(InetSocketAddress groupAddress, LocalAddress localAddress) {
        this.groupAddress = groupAddress;
        this.localAddress = localAddress;
    }

    public void sendMsg(String msg) {
        if (!conn) {
            logger.info(this.localAddress.toString() + "未加入组播，不能发送消息");
            return;
        }
        try {
            logger.info(this.localAddress.toString() + "发送数据:" + msg);
            byte[] data = ("编号[" + Config.INDEX + "], 地址" + this.localAddress.toString() + " 发送数据，内容：" + msg).getBytes(Config.ENCODE);
            DatagramPacket datagramPacket = new DatagramPacket(Unpooled.copiedBuffer(data), this.groupAddress);
            context.writeAndFlush(datagramPacket);
        } catch (Exception e) {
            logger.error("发送数据发生错误", e);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
        logger.info(this.localAddress + "加入组播");
        conn = true;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        InetAddress address = datagramPacket.sender().getAddress();
        ByteBuf buf = datagramPacket.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String content = new String(req, Config.ENCODE);
        String info = "address: " + address;
        logger.info(this.localAddress.toString() + "接收到[" + info + "]发送的内容" + content);
    }

    public void close() {
        logger.info(this.localAddress.toString() + "退出组播");
        this.context.close();
        conn = false;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
    }
}
