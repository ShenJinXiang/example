package com.shenjinxiang.netty.core;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;

public class NettyMulticastUdp implements Runnable {

    private InetSocketAddress groupAddress;
    private LocalAddress localAddress;

    public NettyMulticastUdp(InetSocketAddress groupAddress, LocalAddress localAddress) {
        this.groupAddress = groupAddress;
        this.localAddress = localAddress;
    }


    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            NetworkInterface networkInterface = this.localAddress.getNetworkInterface();
            InetAddress localAddress = this.localAddress.getInetAddress();
            Bootstrap bootstrap = new Bootstrap();
            NettyMulticastHandler handler = new NettyMulticastHandler(this.groupAddress, this.localAddress);
            Config.HANDLER_LIST.add(handler);
            bootstrap.group(group)
                    .channelFactory(new ChannelFactory<NioDatagramChannel>() {
                        public NioDatagramChannel newChannel() {
                            return new NioDatagramChannel(InternetProtocolFamily.IPv4);
                        }
                    })
                    .localAddress(localAddress, groupAddress.getPort())
                    .option(ChannelOption.IP_MULTICAST_IF, networkInterface)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        public void initChannel(NioDatagramChannel ch) throws Exception {
                            ch.pipeline().addLast(handler);
                        }
                    });

            NioDatagramChannel ch = (NioDatagramChannel)bootstrap.bind(groupAddress.getPort()).sync().channel();
            ch.joinGroup(groupAddress, networkInterface).sync();
            ch.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
