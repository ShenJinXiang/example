package com.shenjinxiang.transform.io;

import com.shenjinxiang.transform.core.Consts;
import com.shenjinxiang.transform.core.TransformThread;
import com.shenjinxiang.transform.domain.TransformGroup;
import com.shenjinxiang.transform.kit.ThreadPool;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 15:03
 */
public class NettyTcpServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyTcpServerHandler.class);

    private TransformGroup transformGroup;

    public NettyTcpServerHandler(TransformGroup transformGroup) {
        this.transformGroup = transformGroup;
    }

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
        logger.info("建立连接，地址：" + channel.remoteAddress() + " ID: " + channel.id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
//        int readBytes = byteBuf.readableBytes();
//        byte[] bytes = new byte[readBytes];
//        byteBuf.readBytes(bytes);
        String content = msg.toString();
        logger.info("接收到来自[" + ctx.channel().remoteAddress() + "]的消息，数据长度: " + content.getBytes().length);
        ThreadPool.getThread().execute(new TransformThread(this.transformGroup, content.getBytes(Consts.ENCODE)));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("TCP链接，服务端出现错误，地址：" + channel.remoteAddress() + " ID: " + channel.id(), cause);
        ctx.close();
    }

}
