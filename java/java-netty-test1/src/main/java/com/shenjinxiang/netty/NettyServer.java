package com.shenjinxiang.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 17:48
 */
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private static final int port = 5001;

    public void run() throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            ByteBuf byteBuf = Unpooled.copiedBuffer(new byte[]{(byte) 0xEB, (byte) 0x90});
//                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024 * 1024, byteBuf));
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024 * 50));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            server.option(ChannelOption.SO_BACKLOG, 128);
            server.childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = server.bind(port).sync();
            logger.info("服务启动，端口：" + port);
            f.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[]{(byte) 0xEB, (byte) 0x90};

        int[] ints = new int[]{1,2,3};
//        byte[] bytes1 = hexToByte("0xEB");
        ByteBuf byteBuf = Unpooled.copiedBuffer(new byte[]{(byte) 0xEB, (byte) 0x90});
        String str1 = String.format("%02x", bytes[0]);
        String str2 = String.format("%02x", bytes[1]);
        System.out.println("");
    }
}
