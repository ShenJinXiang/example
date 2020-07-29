package com.shenjinxiang.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 17:52
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("建立连接，地址：" + ctx.channel().remoteAddress() + " ID: " + ctx.channel().id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
//            ByteBuf byteBuf = (ByteBuf) msg;
//            int readBytes = byteBuf.readableBytes();
//            byte[] bytes = new byte[readBytes];
//            byteBuf.readBytes(bytes);
//            logger.info("接收到消息, 客户端: " + ctx.channel().remoteAddress() +
////                    "\n\t 内容：" + msg +
//                    "\n\t 内容：" + new String(bytes, "utf-8") +
//                    "\n\t count: " + count
//            );
            String content = msg.toString();
            logger.info("content: " + content +
                    "\n\t count: " +count);
            count++;
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
