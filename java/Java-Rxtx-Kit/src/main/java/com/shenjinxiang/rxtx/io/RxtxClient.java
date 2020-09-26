package com.shenjinxiang.rxtx.io;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.rxtx.RxtxChannel;
import io.netty.channel.rxtx.RxtxChannelConfig;
import io.netty.channel.rxtx.RxtxDeviceAddress;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RxtxClient implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RxtxClient.class);

    private final RxtxChannel CHANNEL;
    private String serialNumber;

    public RxtxClient(String serialNumber) {
        CHANNEL = new RxtxChannel();
        RxtxChannelConfig channelConfig = CHANNEL.config();
        channelConfig.setBaudrate(9600);
        channelConfig.setDatabits(RxtxChannelConfig.Databits.DATABITS_8);
        channelConfig.setParitybit(RxtxChannelConfig.Paritybit.NONE);
        channelConfig.setStopbits(RxtxChannelConfig.Stopbits.STOPBITS_1);
        this.serialNumber = serialNumber;
    }

    @Override
    public void run() {
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            final RxtxChannel channel = new RxtxChannel();
            System.out.println(channel);
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(channel.getClass())
                    .handler(new ChannelInitializer<RxtxChannel>() {
                        @Override
                        protected void initChannel(RxtxChannel rxtxChannel) throws Exception {
                            rxtxChannel.pipeline().addLast(new FixedLengthFrameDecoder(38));
//                            rxtxChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
//                            rxtxChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                            rxtxChannel.pipeline().addLast(new RxtxHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect(new RxtxDeviceAddress(this.serialNumber)).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("出错了", e);
        } finally {
            group.shutdownGracefully();
            logger.info("退出串口通信");
        }
    }
}
