package com.shenjinxiang.transform.io;

import com.shenjinxiang.transform.domain.TransformGroup;
import com.shenjinxiang.transform.domain.TransformSend;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 15:00
 */
public class NettyTcpClient implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NettyTcpClient.class);

    private TransformSend transformSend;
    private EventLoopGroup group;
    private Bootstrap client;
    private String serverIp;
    private int serverPort;

    public NettyTcpClient(TransformSend transformSend) {
        this.transformSend = transformSend;
        this.serverIp = this.transformSend.getIp();
        this.serverPort = this.transformSend.getPort();
        group = new NioEventLoopGroup();
        client = new Bootstrap();
        final NettyTcpClientHandler handler = new NettyTcpClientHandler(this.transformSend);
        client.group(group);
        client.channel(NioSocketChannel.class);
        client.handler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new LineBasedFrameDecoder(1024 * 50));
                channel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                channel.pipeline().addLast(handler);
                channel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
            }
        });
    }

    @Override
    public void run() {
        try {
            ChannelFuture channelFuture = client.connect(this.serverIp, this.serverPort).sync();
            logger.info("TCP客户端启动，目标服务器: [" + this.serverIp + ":" + this.serverPort + "]");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.info("启动TCP客户端，目标服务器: [" + this.serverIp + ":" + this.serverPort + "] 出错", e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
