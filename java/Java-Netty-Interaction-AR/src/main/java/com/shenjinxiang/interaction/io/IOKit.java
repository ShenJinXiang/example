package com.shenjinxiang.interaction.io;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.io.handler.ArTcpHandler;
import com.shenjinxiang.interaction.io.handler.TcpHandler;
import com.shenjinxiang.interaction.kit.ThreadPool;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class IOKit {

    private static final int MAX_TCP_DATA_LENGTH;
    private static final int SERVER_PORT;

    private static final TcpHandler<String> AR_HANDLER;

    static {
        MAX_TCP_DATA_LENGTH = Config.AR_CONFIG.getMaxTcpDataLength();
        SERVER_PORT = Config.AR_CONFIG.getServerPort();
        AR_HANDLER = new ArTcpHandler<String>();
    }

    public static void runTcpServer() {
        ThreadPool.getThread().execute(new TcpServer(
                SERVER_PORT,
                new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new LineBasedFrameDecoder(MAX_TCP_DATA_LENGTH));
                        channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                        channel.pipeline().addLast(AR_HANDLER);
                        channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
                    }
                }
        ));
    }

    public static boolean isTcpConn() {
        return AR_HANDLER.isConn();
    }

    public static void sendTcpMsg(String msg) {
        AR_HANDLER.sendMsg(msg);
    }

}
