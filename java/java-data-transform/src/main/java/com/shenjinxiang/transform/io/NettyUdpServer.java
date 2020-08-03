package com.shenjinxiang.transform.io;

import com.shenjinxiang.transform.domain.TransformGroup;
import com.shenjinxiang.transform.domain.TransformReceive;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
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

    private TransformGroup transformGroup;
    private Bootstrap server;
    private int port;

    public NettyUdpServer(TransformGroup transformGroup) {
        this.transformGroup = transformGroup;
        this.port = this.transformGroup.getReceive().getPort();
        server = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        server.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new NettyUdpServerHandler(this.transformGroup));
    }

    @Override
    public void run() {
        try {
            ChannelFuture channelFuture = server.bind(port).sync();
            logger.info("UDP服务启动，监听端口：" + port);
            channelFuture.channel().closeFuture().await();
        } catch (InterruptedException e) {
            logger.error("启动UDP服务，端口[" + this.port + "]发生错误", e);
        }
    }
}
