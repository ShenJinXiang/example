package com.shenjinxiang.rs232.demo4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.rxtx.RxtxChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RxtxClient {

    private static final Logger logger = LoggerFactory.getLogger(RxtxClient.class);

    public static void main(String[] args) {
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channelFactory(new ChannelFactory<RxtxChannel>() {
                        @Override
                        public RxtxChannel newChannel() {
                            return null;
                        }
                    });

        } catch (Exception e) {
            logger.error("出错了", e);
        }
    }
}
