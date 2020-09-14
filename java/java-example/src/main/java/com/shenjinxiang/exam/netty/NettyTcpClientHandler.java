package com.shenjinxiang.exam.netty;

import com.shenjinxiang.exam.kit.ByteKit;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/1 22:19
 */
public class NettyTcpClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyTcpClientHandler.class);

    private ChannelHandlerContext context;
    private Channel channel;
    private boolean conn = false;

    public void sendMsg(byte[] bytes) {
        logger.info("发送数据，长度:" + bytes.length);
        this.channel.writeAndFlush(bytes);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.context = ctx;
        channel = ctx.channel();
        logger.info("建立链接，服务器：" + channel.remoteAddress());
        conn = true;
        logger.info("fasong...");
        String str = "00000014000101380103020403010402000300000000000000000000000000000000";
//        byte[] bytes = ByteKit.hexStrToByteArray("00000014000101380103020403010402000300000000000000000000000000000000");
//        this.channel.writeAndFlush(Unpooled.copiedBuffer(bytes));
        this.channel.writeAndFlush(str);
//        while (true) {
//            System.out.println("fs");
//            Thread.sleep(2000);
//        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        logger.info("0000");
//        ByteBuf byteBuf = (ByteBuf) msg;
//        int readBytes = byteBuf.readableBytes();
//        byte[] bytes = new byte[readBytes];
//        byteBuf.readBytes(bytes);
//
//        String content = ByteKit.byteArrayToHexStr(bytes);
//        System.out.println("接收到的数据：" + content);
//        System.out.println("长度:" + bytes.length);

        channel = ctx.channel();
        String content1 = msg.toString();
        logger.info("接收到内容：" + content1);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("TCP链接，服务端出现错误", cause);
        ctx.close();
        conn = false;
    }

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
