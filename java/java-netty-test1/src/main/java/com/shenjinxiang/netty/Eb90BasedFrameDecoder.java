package com.shenjinxiang.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/29 16:49
 */
public class Eb90BasedFrameDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println(byteBuf);
        System.out.println(list);
        int readBytes = byteBuf.readableBytes();
        byte[] bytes = new byte[readBytes];
        byteBuf.readBytes(bytes);

    }
}
