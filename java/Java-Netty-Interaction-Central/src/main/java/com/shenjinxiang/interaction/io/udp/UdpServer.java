package com.shenjinxiang.interaction.io.udp;

import com.shenjinxiang.interaction.io.udp.handler.UdpHandler;
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
public class UdpServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(UdpServer.class);

    private int listenPort;
    private UdpHandler udpHandler;

    public UdpServer(int listenPort, UdpHandler udpHandler) {
        this.listenPort = listenPort;
        this.udpHandler = udpHandler;
    }

    @Override
    public void run() {
        EventLoopGroup group = null;
        try {
            Bootstrap client = new Bootstrap();
            group = new NioEventLoopGroup();
            client.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(this.udpHandler);
            Channel channel = client.bind(listenPort).sync().channel();
            logger.info("正在启动UDP服务，监听端口: " + this.listenPort);
            channel.closeFuture().await();
        } catch (Exception e) {
            logger.info("启动UDP服务，监听端口: [" + this.listenPort + "] 出错", e);
        } finally {
            if (null != group) {
                group.shutdownGracefully();
                logger.info("关闭[" + this.listenPort + "]端口的UDP监听服务");
            }
        }
    }
}
