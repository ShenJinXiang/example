package com.shenjinxiang.netty.io;

import com.shenjinxiang.netty.kit.ByteKit;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class UdpMulticastHandler extends UdpHandler {

    private static final Logger logger = LoggerFactory.getLogger(UdpMulticastHandler.class);

    private InetSocketAddress groupAddress;

    public UdpMulticastHandler(InetSocketAddress groupAddress) {
        this.groupAddress = groupAddress;
    }

    @Override
    protected void active(ChannelHandlerContext ctx) {
        logger.info("加入组播: " + this.groupAddress.getAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        ByteBuf buf = datagramPacket.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String content = ByteKit.byteArrayToHexStr(req);
        logger.info("接收到数据：" + content);
    }
}
