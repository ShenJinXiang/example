package com.shenjinxiang.interaction.io.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/12 07:31
 */
public class TcpClient implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(TcpClient.class);

    private String serverHost;
    private int serverPort;
    private ChannelInitializer channelInitializer;


    public TcpClient(String serverHost, int serverPort, ChannelInitializer channelInitializer) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.channelInitializer = channelInitializer;
    }

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap client = new Bootstrap();
        try {
            client.group(group);
            client.channel(NioSocketChannel.class);
            client.handler(this.channelInitializer);
            ChannelFuture channelFuture = client.connect(this.serverHost, this.serverPort).sync();
            logger.info("TCP客户端启动，目标服务器: [" + this.serverHost + ":" + this.serverPort + "]");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.info("启动TCP客户端，目标服务器: [" + this.serverHost + ":" + this.serverPort + "] 出错", e);
        } finally {
            group.shutdownGracefully();
            logger.info("TCP客户端关闭！");
        }

    }
}
