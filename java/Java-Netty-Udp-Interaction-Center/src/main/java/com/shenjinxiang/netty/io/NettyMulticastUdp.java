package com.shenjinxiang.netty.io;

import com.shenjinxiang.netty.core.Config;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NettyMulticastUdp implements Runnable {

    private InetSocketAddress groupAddress;
    private String networkInterfaceName;

    public NettyMulticastUdp(InetSocketAddress groupAddress, String networkInterfaceName ) {
        this.groupAddress = groupAddress;
        this.networkInterfaceName = networkInterfaceName;
    }

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            InetAddress localAddress = null;
            NetworkInterface networkInterface = null;
            Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
            while (nifs.hasMoreElements()) {
                NetworkInterface ni = nifs.nextElement();
                if (this.networkInterfaceName.equalsIgnoreCase(ni.getName())) {
                    Enumeration<InetAddress> address = ni.getInetAddresses();
                    while (address.hasMoreElements()) {
                        InetAddress addr = address.nextElement();
                        if (addr instanceof Inet4Address) {
                            networkInterface = ni;
                            System.out.println("网络接口名称为：" + ni.getName());
                            System.out.println("网卡接口地址：" + addr.getHostAddress());
                            System.out.println();
                            localAddress = addr;
                        }
                    }

                }
            }


            Bootstrap bootstrap = new Bootstrap();
            Config.MULTICAST_HANDLER = new NettyMulticastHandler(this.groupAddress);
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
                            ch.pipeline().addLast(Config.MULTICAST_HANDLER);
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
