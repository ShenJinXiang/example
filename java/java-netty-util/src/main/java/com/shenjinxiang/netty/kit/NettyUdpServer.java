package com.shenjinxiang.netty.kit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 11:14
 */
public class NettyUdpServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NettyUdpServer.class);

    private Bootstrap server;
    private int port;

    public NettyUdpServer(int port) {
        this.port = port;
        server = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        server.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new NettyUdpServerHandler());
    }

    @Override
    public void run() {
        try {
            server.bind(port).sync().channel().closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
