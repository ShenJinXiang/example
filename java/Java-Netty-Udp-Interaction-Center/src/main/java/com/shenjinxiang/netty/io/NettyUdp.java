package com.shenjinxiang.netty.io;

import com.shenjinxiang.netty.core.Config;
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
public class NettyUdp implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NettyUdp.class);

    private int port;

    public NettyUdp(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        EventLoopGroup group = null;
        try {
            Config.UDP_HANDLER = new UdpHandler();
            Bootstrap client = new Bootstrap();
            group = new NioEventLoopGroup();
            client.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(Config.UDP_HANDLER);
            Channel channel = client.bind(port).sync().channel();
            logger.info("正在启动UDP服务，监听端口: " + this.port);
            channel.closeFuture().await();
        } catch (Exception e) {
            logger.info("启动UDP服务，监听端口: [" + this.port + "] 出错", e);
        } finally {
            if (null != group) {
                group.shutdownGracefully();
            }
        }
    }
}
