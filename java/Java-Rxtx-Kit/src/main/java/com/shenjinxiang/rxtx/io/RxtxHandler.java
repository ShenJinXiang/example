package com.shenjinxiang.rxtx.io;

import com.shenjinxiang.rxtx.kit.ByteArrayConveter;
import com.shenjinxiang.rxtx.kit.ByteKit;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@ChannelHandler.Sharable
public class RxtxHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RxtxHandler.class);

    private boolean conn = false;
    private ChannelHandlerContext ctx;

    public void sendData(byte[] bytes) {
        if (conn) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
            this.ctx.channel().writeAndFlush(byteBuf);
        }
    }

    public void close() {
        if (conn) {
            this.ctx.close();
            this.conn = false;
            logger.info("退出串口通信！");
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        this.ctx = ctx;
        this.conn = true;
        logger.info("建立连接，地址：" + ctx.channel().remoteAddress() + " ID: " + ctx.channel().id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.ctx = ctx;
        ByteBuf byteBuf = (ByteBuf) msg;
        int readBytes = byteBuf.readableBytes();
        byte[] bytes = new byte[readBytes];
        byteBuf.readBytes(bytes);
        DataParser.parse(bytes);
    }

    public boolean isConn() {
        return conn;
    }

    public void setConn(boolean conn) {
        this.conn = conn;
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
