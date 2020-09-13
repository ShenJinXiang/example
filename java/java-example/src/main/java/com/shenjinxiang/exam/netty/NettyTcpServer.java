package com.shenjinxiang.exam.netty;

import com.shenjinxiang.kit.ThreadPool;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/1 20:48
 */
public class NettyTcpServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NettyTcpServer.class);

    private EventLoopGroup boosGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap server;
    private int port;

    public NettyTcpServer(int port) {
        this.port = port;
        boosGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            ByteBuf byteBuf = Unpooled.copiedBuffer(new byte[]{(byte) 0xEB, (byte) 0x90});
//                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024 * 1024, byteBuf));
//                        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024 * 50));
                        socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(10));
                        socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                        socketChannel.pipeline().addLast(new NettyTcpServerHandler());
                        socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                    }
                });
        server.option(ChannelOption.SO_BACKLOG, 128);
        server.childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    @Override
    public void run() {
        try {
            ChannelFuture channelFuture = server.bind(this.port).sync();
            logger.info("TCP服务启动，监听端口：" + port);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("启动TCP服务，端口[" + this.port + "]发生错误", e);
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        ThreadPool.getThread().execute(new NettyTcpServer(6000));
    }
}
