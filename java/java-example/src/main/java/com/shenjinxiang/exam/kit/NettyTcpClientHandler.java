package com.shenjinxiang.exam.kit;

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

    private String name;
    private Channel channel;

    public NettyTcpClientHandler(String name) {
        this.name = name;
    }

   @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
        logger.info("建立链接，服务器：" + channel.remoteAddress());
        String str = "中华人民共和国，中央人民政府！";
        for (int i = 0; i < 100; i++) {
            logger.info("发送消息：" + i);
            channel.writeAndFlush(str + "[" + this.name + ":" + i + "]\n");
            Thread.sleep(2000);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        channel = ctx.channel();
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
