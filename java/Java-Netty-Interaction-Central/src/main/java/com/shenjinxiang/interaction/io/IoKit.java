package com.shenjinxiang.interaction.io;

import com.shenjinxiang.interaction.core.Consts;
import com.shenjinxiang.interaction.kit.ThreadPool;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/12 06:54
 */
public class IoKit {

    /**
     * 启动和qt对接的tcp服务端
     */
    public static void runQtServer() {
        ThreadPool.getThread().execute(new LineBaseTcpService(
                Consts.QT_SERVER_LISTEN_PORT,
                Consts.MAX_TCP_DATA_LENGTH,
                Consts.QT_HANDLER
        ));
    }

    /**
     * 启动AR对接的tcp客户端
     */
    public static void runArClient() {
        ThreadPool.getThread().execute(new TcpClient(
                Consts.AR_SERVER_HOST,
                Consts.AR_SERVER_PORT,
                new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new LineBasedFrameDecoder(Consts.MAX_TCP_DATA_LENGTH));
                        channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                        channel.pipeline().addLast(Consts.AR_HANDLER);
                        channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
                    }
                }
        ));
    }


    /**
     * 启动算法中心对接的TCP客户端
     */
    public static void runAlgClient() {
        ThreadPool.getThread().execute(new TcpClient(
                Consts.ALG_SERVER_HOST,
                Consts.ALG_SERVER_PORT,
                new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new FixedLengthFrameDecoder(Consts.ALG_FIXED_DATA_LENGTH));
                        channel.pipeline().addLast(Consts.ALG_HANDLER);
                    }
                }
        ));
    }
}
