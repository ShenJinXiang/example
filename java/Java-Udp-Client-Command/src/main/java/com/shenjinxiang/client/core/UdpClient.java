package com.shenjinxiang.client.core;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 14:52
 */
public class UdpClient implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(UdpClient.class);

    private Bootstrap client;
    private EventLoopGroup group;
    private int port;

    public UdpClient(int port) {
        this.port = port;
        client = new Bootstrap();
        group = new NioEventLoopGroup();
        client.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new UdpClientHandler());
    }

    @Override
    public void run() {
        try {
            Channel channel = client.bind(port).sync().channel();
            logger.info("启动UDP服务，监听端口: " + this.port);
            channel.closeFuture().await();
        } catch (Exception e) {
            logger.info("启动UDP服务，监听端口: [" + this.port + "] 出错", e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
