package com.shenjinxiang.netty.io;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class UdpMulticastServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(UdpMulticastServer.class);

    private InetSocketAddress groupAddress;
    private String networkInterfaceName;
    private UdpHandler udpHandler;

    public UdpMulticastServer(InetSocketAddress groupAddress, String networkInterfaceName, UdpHandler udpHandler) {
        this.groupAddress = groupAddress;
        this.networkInterfaceName = networkInterfaceName;
        this.udpHandler = udpHandler;
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
                            logger.info("网络接口名称为: " + ni.getName() + "\t网卡接口地址：" + addr.getHostAddress());
                            localAddress = addr;
                        }
                    }

                }
            }

            Bootstrap bootstrap = new Bootstrap();
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
                            ch.pipeline().addLast(udpHandler);
                        }
                    });

            NioDatagramChannel ch = (NioDatagramChannel)bootstrap.bind(groupAddress.getPort()).sync().channel();
            ch.joinGroup(groupAddress, networkInterface).sync();
            logger.info("正在加入组播: " + this.groupAddress.getAddress());
            ch.closeFuture().await();
        } catch (Exception e) {
            logger.error("加入组播出错，地址: " + this.groupAddress.getAddress(), e);
        } finally {
            group.shutdownGracefully();
            logger.info("退出UDP组播，地址: " + this.groupAddress.getAddress());
        }
    }
}
