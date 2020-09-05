package com.shenjinxiang.netty.core;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.NetUtil;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NettyMulticastUdp implements Runnable {

    private InetSocketAddress groupAddress;

    public NettyMulticastUdp(InetSocketAddress groupAddress) {
        this.groupAddress = groupAddress;
    }

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            InetAddress localAddress = null;
//            NetworkInterface networkInterface = NetUtil.LOOPBACK_IF;
//            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
//            while (addresses.hasMoreElements()) {
//                InetAddress address = addresses.nextElement();
//                if (address instanceof Inet4Address){
//                    System.out.println("网卡接口地址：" + address.getHostAddress());
//                    System.out.println();
//                    localAddress = address;
//                }
//            }


            NetworkInterface networkInterface = NetUtil.LOOPBACK_IF;
            Enumeration<NetworkInterface> nifs = null;
            nifs = NetworkInterface.getNetworkInterfaces();
            while (nifs.hasMoreElements()) {
                NetworkInterface ni = nifs.nextElement();
                if ("wlan1".equalsIgnoreCase(ni.getName())) {
                    networkInterface = ni;
                    Enumeration<InetAddress> address = ni.getInetAddresses();
                    while (address.hasMoreElements()) {
                        InetAddress addr = address.nextElement();
                        if (addr instanceof Inet4Address) {
                            System.out.println("网络接口名称为：" + ni.getName());
                            System.out.println("网卡接口地址：" + addr.getHostAddress());
                            System.out.println();
                            localAddress = addr;
                        }
                    }

                }
            }


            Bootstrap bootstrap = new Bootstrap();
            Config.HANDLER = new NettyMulticastHandler(this.groupAddress);
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
                            ch.pipeline().addLast(Config.HANDLER);
                        }
                    });

            NioDatagramChannel ch = (NioDatagramChannel)bootstrap.bind(groupAddress.getPort()).sync().channel();
            ch.joinGroup(groupAddress, networkInterface).sync();
            System.out.println("server");
            ch.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        InetSocketAddress groupAddress = new InetSocketAddress("224.255.10.0", 9999);
        new NettyMulticastUdp(groupAddress).run();
    }
}
