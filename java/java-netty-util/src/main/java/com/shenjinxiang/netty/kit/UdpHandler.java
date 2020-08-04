package com.shenjinxiang.netty.kit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 11:16
 */
public class UdpHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger logger = LoggerFactory.getLogger(UdpHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Channel channel = ctx.channel();
        logger.info("建立链接，服务器：" + channel.remoteAddress());
//        for (int i = 0; i < 100; i++) {
//            channel.writeAndFlush("123\n");
//        }
        String str = "中华人民共和国，中央人民政府！\n";
        logger.info("发送消息：" + str);
        channel.writeAndFlush(str);
//
//        // 由于数据报的数据是以字符数组传的形式存储的，所以传转数据
//        byte[] bytes = str.getBytes("UTF-8");
//        DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), datagramPacket.sender());
//        ctx.writeAndFlush(data);//向客户端发送消息
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        //做类型转换，将msg转换成Netty的ByteBuf对象。
        //ByteBuf类似于JDK中的java.nio.ByteBuffer 对象，不过它提供了更加强大和灵活的功能。
        ByteBuf buf = datagramPacket.copy().content();

        //通过ByteBuf的readableBytes方法可以获取缓冲区可读的字节数，
        //根据可读的字节数创建byte数组
        byte[] req = new byte[buf.readableBytes()];
        //通过ByteBuf的readBytes方法将缓冲区中的字节数组复制到新建的byte数组中
        buf.readBytes(req);
    }
}