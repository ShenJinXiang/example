package com.shenjinxiang.interaction.io;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.io.handler.QtTcpHandler;
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
    private static final String CENTRAL_SERVER_HOST;
    private static final int CENTRAL_SERVER_PORT;

    private static final TcpHandler<String> QT_HANDLER;

    static {
        MAX_TCP_DATA_LENGTH = Config.MAX_TCP_DATA_LENGTH;
        CENTRAL_SERVER_HOST = Config.QT_CONFIG.getCentralServerHost();
        CENTRAL_SERVER_PORT = Config.QT_CONFIG.getCentralServerPort();
        QT_HANDLER = new QtTcpHandler<String>();
    }

    public static void runTcpClient() {
        if (!QT_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new TcpClient(
                    CENTRAL_SERVER_HOST,
                    CENTRAL_SERVER_PORT,
                    new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new LineBasedFrameDecoder(MAX_TCP_DATA_LENGTH));
                            channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                            channel.pipeline().addLast(QT_HANDLER);
                            channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
                        }
                    }
            ));
        }
    }

    public static void closeTcpClient() {
        if (QT_HANDLER.isConn()) {
            QT_HANDLER.close();
        }
    }

    public static boolean isCentralTcpConn() {
        return QT_HANDLER.isConn();
    }

    public static void sendCentralMsg(String data) {
        QT_HANDLER.sendMsg(data);
    }
}
