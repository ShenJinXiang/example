package com.shenjinxiang.transform.io;

import com.shenjinxiang.transform.core.TransformThread;
import com.shenjinxiang.transform.domain.TransformGroup;
import com.shenjinxiang.transform.kit.ThreadPool;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 15:23
 */
public class NettyUdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger logger = LoggerFactory.getLogger(NettyUdpServerHandler.class);

    private TransformGroup transformGroup;

    public NettyUdpServerHandler(TransformGroup transformGroup) {
        this.transformGroup = transformGroup;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        ByteBuf byteBuf = datagramPacket.copy().content();
        int readBytes = byteBuf.readableBytes();
        byte[] bytes = new byte[readBytes];
        byteBuf.readBytes(bytes);
        logger.info("接收到消息，数据长度: " + readBytes);
        ThreadPool.getThread().execute(new TransformThread(this.transformGroup, bytes));
    }
}
