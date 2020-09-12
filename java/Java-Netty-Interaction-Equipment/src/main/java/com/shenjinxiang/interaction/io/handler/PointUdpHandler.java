package com.shenjinxiang.interaction.io.handler;

import com.shenjinxiang.interaction.kit.ByteKit;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointUdpHandler extends UdpHandler {

    private static final Logger logger = LoggerFactory.getLogger(PointUdpHandler.class);

    private int listenPort;

    public PointUdpHandler(int listenPort) {
        this.listenPort = listenPort;
    }

    @Override
    protected void active(ChannelHandlerContext ctx) {
        logger.info("UDP服务启动，监听端口: " + this.listenPort);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        ByteBuf buf = datagramPacket.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String content = ByteKit.byteArrayToHexStr(req);
        logger.info("接收到内容：" + content);
    }
}
