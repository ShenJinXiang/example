package com.shenjinxiang.interaction.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName TcpServer
 * @Author ShenjinXiang
 * @Date 2020/9/11 0011 22:59
 */
public class TcpServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(TcpServer.class);

    private int listenPort;
    private ChannelInitializer channelInitializer;

    public TcpServer(int listenPort, ChannelInitializer channelInitializer) {
        this.listenPort = listenPort;
        this.channelInitializer = channelInitializer;
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
                    .childHandler(this.channelInitializer);
            server.option(ChannelOption.SO_BACKLOG, 128);
            server.childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = server.bind(this.listenPort).sync();
            logger.info("TCP服务启动，监听端口: " + this.listenPort);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("启动TCP服务，端口[" + this.listenPort + "]发生错误", e);
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            logger.info("TCP服务关闭，监听端口: " + this.listenPort);
        }
    }
}
