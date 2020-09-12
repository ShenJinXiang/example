package com.shenjinxiang.interaction.io.udp.handler;

import com.shenjinxiang.interaction.kit.ByteKit;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

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
        String content1 = new String(req, StandardCharsets.UTF_8);
        String content2 = new String(req, StandardCharsets.UTF_16);
        String content3 = new String(req, StandardCharsets.ISO_8859_1);
        logger.info("接收到内容：" + content);
        logger.info("接收到内容1：" + content1);
        logger.info("接收到内容2：" + content2);
        logger.info("接收到内容3：" + content3);
        logger.info("来源:" + datagramPacket.sender().getAddress());
    }
}
