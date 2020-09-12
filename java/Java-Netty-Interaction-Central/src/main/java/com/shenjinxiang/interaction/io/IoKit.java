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
public class IOKit {

    private static final int MAX_TCP_DATA_LENGTH;

    private static final int QT_SERVER_LISTEN_PORT;

    private static final String AR_SERVER_HOST;
    private static final int AR_SERVER_PORT;

    private static final String ALG_SERVER_HOST;
    private static final int ALG_SERVER_PORT;
    private static final int ALG_FIXED_DATA_LENGTH;

    private static final int UDP_SERVER_LISTEN_PORT;

    private static final String UDP_MULTICAST_NETWOKINTERFACE;
    private static final String UDP_MULTICAST_HOST;
    private static final int UDP_MULTICAST_PORT;


    private static final TcpHandler<String> QT_HANDLER;
    private static final TcpHandler<String> AR_HANDLER;
    private static final TcpHandler<byte[]> ALG_HANDLER;

    private static final InetSocketAddress MULTICAST_GROUP_ADDRESS;
    private static final UdpHandler POINT_HANDLER;
    private static final UdpHandler MULTICAST_HANDLER;

    static {
        MAX_TCP_DATA_LENGTH = Config.CENTRAL_CONFIG.getMaxTcpDataLength();
        QT_SERVER_LISTEN_PORT = Config.CENTRAL_CONFIG.getQtServerListenPort();

        AR_SERVER_HOST = Config.CENTRAL_CONFIG.getArServerHost();
        AR_SERVER_PORT = Config.CENTRAL_CONFIG.getArServerPort();

        ALG_SERVER_HOST = Config.CENTRAL_CONFIG.getAlgServerHost();
        ALG_SERVER_PORT = Config.CENTRAL_CONFIG.getAlgServerPort();
        ALG_FIXED_DATA_LENGTH = Config.CENTRAL_CONFIG.getAlgFixedDataLength();

        UDP_SERVER_LISTEN_PORT = Config.CENTRAL_CONFIG.getUdpServerListenPort();

        UDP_MULTICAST_NETWOKINTERFACE = Config.CENTRAL_CONFIG.getUdpMulticastNetwokinterface();
        UDP_MULTICAST_HOST = Config.CENTRAL_CONFIG.getUdpMulticastHost();
        UDP_MULTICAST_PORT = Config.CENTRAL_CONFIG.getUdpMulticastPort();

        QT_HANDLER = new QtTcpHandler<String>();
        AR_HANDLER = new ArTcpHandler<String>();
        ALG_HANDLER = new AlgTcpHandler<byte[]>();

        MULTICAST_GROUP_ADDRESS = new InetSocketAddress(UDP_MULTICAST_HOST, UDP_MULTICAST_PORT);
        POINT_HANDLER = new PointUdpHandler(UDP_SERVER_LISTEN_PORT);
        MULTICAST_HANDLER = new UdpMulticastHandler(MULTICAST_GROUP_ADDRESS);
    }

    /**
     * 启动和qt对接的tcp服务端
     */
    public static void runQtServer() {
        if (!QT_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new TcpServer(
                    QT_SERVER_LISTEN_PORT,
                    createLineBaseChannelInitializer(QT_HANDLER)
            ));

        }
    }

    /**
     * 启动AR对接的tcp客户端
     */
    public static void runArClient() {
        if (!AR_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new TcpClient(
                    AR_SERVER_HOST,
                    AR_SERVER_PORT,
                    createLineBaseChannelInitializer(AR_HANDLER)
            ));
        }
    }


    /**
     * 启动算法中心对接的TCP客户端
     */
    public static void runAlgClient() {
        if (ALG_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new TcpClient(
                    ALG_SERVER_HOST,
                    ALG_SERVER_PORT,
                    new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new FixedLengthFrameDecoder(ALG_FIXED_DATA_LENGTH));
                            channel.pipeline().addLast(ALG_HANDLER);
                        }
                    }
            ));
        }
    }

    /**
     * 启动UPD服务 发送udp数据
     */
    public static void runPointUdpServer() {
        if (!POINT_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new UdpServer(
                    UDP_SERVER_LISTEN_PORT,
                    POINT_HANDLER
            ));
        }
    }

    /**
     * 启动UDP 组播
     */
    public static void runMulticastUdpServer() {
        if (!MULTICAST_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new UdpMulticastServer(
                    MULTICAST_GROUP_ADDRESS,
                    UDP_MULTICAST_NETWOKINTERFACE,
                    MULTICAST_HANDLER
            ));
        }
    }

    private static ChannelInitializer createLineBaseChannelInitializer(TcpHandler tcpHandler) {
        return new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new LineBasedFrameDecoder(MAX_TCP_DATA_LENGTH));
                channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                channel.pipeline().addLast(tcpHandler);
                channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
            }
        };
    }

}
