package com.shenjinxiang.data.core;

import com.shenjinxiang.data.kit.StrKit;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/1 20:51
 */
public class NettyTcpServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyTcpServerHandler.class);

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
        Consts.channel = ctx.channel();
        logger.info("建立连接，地址：" + channel.remoteAddress() + " ID: " + channel.id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        channel = ctx.channel();
        String content = msg.toString();
        logger.info("接收到内容：" + content);
        if (!Consts.ISSENDMSG && "start".equalsIgnoreCase(content)) {
            Consts.ISSENDMSG = true;
        }
        if (Consts.ISSENDMSG && "end".equalsIgnoreCase(content)) {
            Consts.ISSENDMSG = false;
        }
        if (StrKit.notBlank(content) && "exit".equalsIgnoreCase(content.trim())) {
            System.exit(0);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("TCP链接，服务端出现错误", cause);
        ctx.close();
    }
}
