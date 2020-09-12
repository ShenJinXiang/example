package com.shenjinxiang.interaction.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/12 06:46
 */
public class LineBaseTcpService implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(LineBaseTcpService.class);

    private int listenPort;
    private int maxDataLength;
    private TcpHandler tcpHandler;

    public LineBaseTcpService(int listenPort, int maxDataLength, TcpHandler tcpHandler) {
        this.listenPort = listenPort;
        this.maxDataLength = maxDataLength;
        this.tcpHandler = tcpHandler;
    }

    @Override
    public void run() {
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
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(maxDataLength));
                            socketChannel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                            socketChannel.pipeline().addLast(tcpHandler);
                            socketChannel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
                        }
                    });
            server.option(ChannelOption.SO_BACKLOG, 128);
            server.childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = server.bind(this.listenPort).sync();
            logger.info("TCP服务启动，监听端口：" + listenPort);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("启动TCP服务，端口[" + this.listenPort + "]发生错误", e);
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
