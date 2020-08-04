package com.shenjinxiang.transform.io;

import com.shenjinxiang.transform.domain.TransformGroup;
import com.shenjinxiang.transform.domain.TransformReceive;
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
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 15:00
 */
public class NettyTcpServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NettyTcpServer.class);

    private TransformGroup transformGroup;
    private int port;

    private EventLoopGroup boosGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap server;

    public NettyTcpServer(TransformGroup transformGroup) {
        this.transformGroup = transformGroup;
        this.port = this.transformGroup.getReceive().getPort();

        NettyTcpServerHandler serverHandler = new NettyTcpServerHandler(this.transformGroup);
        boosGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024 * 50));
                        socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                        socketChannel.pipeline().addLast(serverHandler);
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
}