package com.shenjinxiang.rxtx.io;

import com.shenjinxiang.rxtx.kit.ByteArrayConveter;
import com.shenjinxiang.rxtx.kit.ByteKit;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RxtxHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RxtxHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel);
        logger.info("建立连接，地址：" + ctx.channel().remoteAddress() + " ID: " + ctx.channel().id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        int readBytes = byteBuf.readableBytes();
        byte[] bytes = new byte[readBytes];
        byteBuf.readBytes(bytes);
        DataParser.parse(bytes);
    }


    public static void main(String[] args) {
        String str = "10101110";
//        StringBuffer stringBuffer = new StringBuffer(str);
//        System.out.println(stringBuffer.toString());
//        stringBuffer.reverse();
//        System.out.println(stringBuffer.toString());
        Pattern p = Pattern.compile("[0]");
        Matcher matcher = p.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.start());
        }

        int max = Integer.MAX_VALUE;
        System.out.println(max);
        System.out.println(max / 100);
        System.out.println(max / 100 / 60);
        System.out.println(max / 100 / 60 / 60);
    }
}
