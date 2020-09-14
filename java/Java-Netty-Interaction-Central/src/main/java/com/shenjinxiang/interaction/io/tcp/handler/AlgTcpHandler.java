package com.shenjinxiang.interaction.io.tcp.handler;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.io.IOKit;
import com.shenjinxiang.interaction.kit.ByteKit;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/12 07:54
 */
public class AlgTcpHandler<T> extends TcpHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(AlgTcpHandler.class);

    private static final String WAVE_DATA_PREFIX = Config.WAVE_DATA_PREFIX;

    /**
     * 发送消息
     * @param msg
     */
    @Override
    public void sendMsg(T msg) {
        if (conn) {
            this.channel.writeAndFlush(msg);
        }
    }

    /**
     * 接收消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
//        int readBytes = byteBuf.readableBytes();
//        byte[] bytes = new byte[readBytes];
//        byteBuf.readBytes(bytes);
//        String content = WAVE_DATA_PREFIX + ByteKit.byteArrayToHexStr(bytes);
//        logger.info("content:" + content);
//        IOKit.sendArTcpMsg(content);

        channel = ctx.channel();
        String content = WAVE_DATA_PREFIX + msg.toString();
//        logger.info("content:" + content);
        IOKit.sendArTcpMsg(content);
    }
}
