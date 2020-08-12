package com.shenjinxiang.netty.kit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 14:53
 */
public class NettyUdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger logger = LoggerFactory.getLogger(NettyUdpClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Consts.CHANNEL = ctx.channel();
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
        String content = new String(req);
        logger.info("内容：" + content);

//        String json = "hello world!!";
//        // 由于数据报的数据是以字符数组传的形式存储的，所以传转数据
//        byte[] bytes = json.getBytes("UTF-8");
//        DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), new InetSocketAddress("", 5005));
//        channelHandlerContext.writeAndFlush(data);//向客户端发送消息
    }
}
