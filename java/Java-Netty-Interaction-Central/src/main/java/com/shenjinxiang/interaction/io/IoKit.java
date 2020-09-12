package com.shenjinxiang.interaction.io;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.io.tcp.handler.AlgTcpHandler;
import com.shenjinxiang.interaction.io.tcp.handler.ArTcpHandler;
import com.shenjinxiang.interaction.io.tcp.handler.QtTcpHandler;
import com.shenjinxiang.interaction.io.tcp.handler.TcpHandler;
import com.shenjinxiang.interaction.io.tcp.TcpClient;
import com.shenjinxiang.interaction.io.tcp.TcpServer;
import com.shenjinxiang.interaction.io.udp.UdpMulticastServer;
import com.shenjinxiang.interaction.io.udp.UdpServer;
import com.shenjinxiang.interaction.io.udp.handler.PointUdpHandler;
import com.shenjinxiang.interaction.io.udp.handler.UdpHandler;
import com.shenjinxiang.interaction.io.udp.handler.UdpMulticastHandler;
import com.shenjinxiang.interaction.kit.ThreadPool;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/12 06:54
 */
public class IoKit {

    private static TcpHandler<String> QT_HANDLER = new QtTcpHandler();
    private static TcpHandler<String> AR_HANDLER = new ArTcpHandler();
    private static TcpHandler<byte[]> ALG_HANDLER = new AlgTcpHandler<>();

    private static InetSocketAddress MULTICAST_GROUP_ADDRESS = new InetSocketAddress(Config.UDP_MULTICAST_HOST, Config.UDP_MULTICAST_PORT);
    private static UdpHandler POINT_HANDLER = new PointUdpHandler(Config.UDP_SERVER_LISTEN_PORT);
    private static UdpHandler MULTICAST_HANDLER = new UdpMulticastHandler(MULTICAST_GROUP_ADDRESS);

    /**
     * 启动和qt对接的tcp服务端
     */
    public static void runQtServer() {
        ThreadPool.getThread().execute(new TcpServer(
                Config.QT_SERVER_LISTEN_PORT,
                createLineBaseChannelInitializer(QT_HANDLER)
        ));
    }

    /**
     * 启动AR对接的tcp客户端
     */
    public static void runArClient() {
        ThreadPool.getThread().execute(new TcpClient(
                Config.AR_SERVER_HOST,
                Config.AR_SERVER_PORT,
                createLineBaseChannelInitializer(AR_HANDLER)
        ));
    }


    /**
     * 启动算法中心对接的TCP客户端
     */
    public static void runAlgClient() {
        ThreadPool.getThread().execute(new TcpClient(
                Config.ALG_SERVER_HOST,
                Config.ALG_SERVER_PORT,
                new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new FixedLengthFrameDecoder(Config.ALG_FIXED_DATA_LENGTH));
                        channel.pipeline().addLast(ALG_HANDLER);
                    }
                }
        ));
    }

    /**
     * 启动UPD服务 发送udp数据
     */
    public static void runPointUdpServer() {
        ThreadPool.getThread().execute(new UdpServer(
                Config.UDP_SERVER_LISTEN_PORT,
                POINT_HANDLER
        ));
    }

    /**
     * 启动UDP 组播
     */
    public static void runMulticastUdpServer() {
        ThreadPool.getThread().execute(new UdpMulticastServer(
                MULTICAST_GROUP_ADDRESS,
                Config.UDP_MULTICAST_NETWOKINTERFACE,
                MULTICAST_HANDLER
        ));
    }

    private static ChannelInitializer createLineBaseChannelInitializer(TcpHandler tcpHandler) {
        return new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new LineBasedFrameDecoder(Config.MAX_TCP_DATA_LENGTH));
                channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                channel.pipeline().addLast(tcpHandler);
                channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
            }
        };
    }
}
