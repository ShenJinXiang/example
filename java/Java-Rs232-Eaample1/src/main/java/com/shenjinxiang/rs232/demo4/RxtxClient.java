package com.shenjinxiang.rs232.demo4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
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

public class RxtxClient {

    private static final Logger logger = LoggerFactory.getLogger(RxtxClient.class);

    public static void main(String[] args) {
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            final RxtxChannel channel = new RxtxChannel();
            System.out.println(channel);
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
//                    .channelFactory(new ChannelFactory<RxtxChannel>() {
//                        public RxtxChannel newChannel() {
//                            return channel;
//                        }
//                    })
                    .channel(channel.getClass())
                    .handler(new ChannelInitializer<RxtxChannel>() {
                        @Override
                        protected void initChannel(RxtxChannel rxtxChannel) throws Exception {
                            rxtxChannel.pipeline().addLast(new FixedLengthFrameDecoder(19));
//                            rxtxChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                            rxtxChannel.pipeline().addLast(new RxtxHandler());
//                            rxtxChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                        }
                    });

            RxtxChannelConfig channelConfig = channel.config();
            channelConfig.setBaudrate(9600);
            channelConfig.setDatabits(RxtxChannelConfig.Databits.DATABITS_8);
            channelConfig.setParitybit(RxtxChannelConfig.Paritybit.NONE);
            channelConfig.setStopbits(RxtxChannelConfig.Stopbits.STOPBITS_1);

            ChannelFuture future = bootstrap.connect(new RxtxDeviceAddress("COM3")).sync();
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            logger.error("出错了", e);
        }
    }
}
