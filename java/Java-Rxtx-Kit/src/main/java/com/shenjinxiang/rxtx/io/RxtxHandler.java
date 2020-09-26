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
        printData(bytes);
    }

    private void printData(byte[] bytes) {
        String hex = ByteKit.byteArrayToHexStr(bytes);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("接收到数据：");
        stringBuffer.append("\n\t字节数组：").append(Arrays.toString(bytes));
        stringBuffer.append("\n\t十六进制：").append(hex);
        String led1Str = ByteKit.getBit(bytes[4]);
        String led2Str = ByteKit.getBit(bytes[5]);
        String led3Str = ByteKit.getBit(bytes[6]);
        String led4Str = ByteKit.getBit(bytes[7]);
        String press1Str = ByteKit.getBit(bytes[8]);
        String press2Str = ByteKit.getBit(bytes[9]);
        String press3Str = ByteKit.getBit(bytes[10]);
        String press4Str = ByteKit.getBit(bytes[11]);
        byte[] bytes1 = new byte[] {0x00, 0x00, bytes[12], bytes[13]};
        byte[] bytes2 = new byte[] {0x00, 0x00, bytes[14], bytes[15]};
        stringBuffer.append("\n\tLED灯数据：")
                .append(led1Str).append("   ")
                .append(led2Str).append("    ")
                .append(led3Str).append("    ")
                .append(led4Str);
        stringBuffer.append("\n\t按键数据：")
                .append(press1Str).append("    ")
                .append(press2Str).append("    ")
                .append(press3Str).append("    ")
                .append(press4Str);
        stringBuffer.append("\n\t摇杆数据：")
                .append(ByteArrayConveter.getInt(bytes1, 0)).append("    ")
                .append(ByteArrayConveter.getInt(bytes2, 0));
        logger.info(stringBuffer.toString());
    }
}
