package com.shenjinxiang.client.io;

import com.shenjinxiang.client.core.Config;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 15:00
 */
public class NettyTcpClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyTcpClientHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Config.CLIENT_CONTEXT = ctx;
        Config.CLIENT_CHANNEL = ctx.channel();
        logger.info("建立链接，服务器：" + Config.CLIENT_CHANNEL.remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Config.CLIENT_CHANNEL = ctx.channel();
        String content = msg.toString();
        logger.info("接收到内容：" + content);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("TCP链接，服务端出现错误", cause);
        ctx.close();
    }
}
