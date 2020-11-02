package com.shenjinxiang.netty.io;

import com.shenjinxiang.netty.kit.ByteKit;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 14:53
 */
public abstract class UdpHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger logger = LoggerFactory.getLogger(UdpHandler.class);

    private boolean conn;
    private ChannelHandlerContext context;

    public void sendMsg(byte[] bytes, InetSocketAddress address) {
        if (!conn) {
            logger.info("不能发送消息");
            return;
        }
        try {
            logger.info("发送消息" +
                    "\n\t目标：" + address +
                    "\n\t内容：" + ByteKit.byteArrayToHexStr(bytes)
            );
            DatagramPacket datagramPacket = new DatagramPacket(Unpooled.copiedBuffer(bytes), address);
            context.channel().writeAndFlush(datagramPacket);
        } catch (Exception e) {
            logger.error("发送数据发生错误", e);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.conn = true;
        this.context = ctx;
        active(ctx);
    }

    protected abstract void active(ChannelHandlerContext ctx);

    @Override
    protected abstract void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception;

    public void close() {
        this.context.close();
        conn = false;
    }

    public boolean isConn() {
        return conn;
    }

    public void setConn(boolean conn) {
        this.conn = conn;
    }

}
