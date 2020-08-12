package com.shenjinxiang.netty.kit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 14:52
 */
public class NettyUdpClient {

    private Bootstrap client;
    private int port;

    public NettyUdpClient(int port) {
        this.port = port;
        Bootstrap client = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new NettyUdpClientHandler());
        Consts.CHANNEL = client.bind(port).channel();
    }

//    @Override
//    public void run() {
//        try {
//            Channel channel = client.bind(port).sync().channel();
//            channel.closeFuture().await();
//        } catch (Exception e) {
//
//        }
//    }
}
