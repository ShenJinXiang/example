package com.shenjinxiang.interaction.io;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.io.handler.PointUdpHandler;
import com.shenjinxiang.interaction.io.handler.UdpHandler;
import com.shenjinxiang.interaction.io.handler.UdpMulticastHandler;
import com.shenjinxiang.interaction.kit.ThreadPool;

import java.net.InetSocketAddress;

public class IOKit {

    private static final int UDP_SERVER_LISTEN_PORT;

    private static final String UDP_MULTICAST_NETWOKINTERFACE;
    private static final String UDP_MULTICAST_HOST;
    private static final int UDP_MULTICAST_PORT;

    private static final InetSocketAddress MULTICAST_GROUP_ADDRESS;
    private static final UdpHandler POINT_HANDLER;
    private static final UdpHandler MULTICAST_HANDLER;

    static {
        UDP_SERVER_LISTEN_PORT = Config.EQUIPMENT_CONFIG.getUdpServerListenPort();
        UDP_MULTICAST_NETWOKINTERFACE = Config.EQUIPMENT_CONFIG.getUdpMulticastNetwokinterface();
        UDP_MULTICAST_HOST = Config.EQUIPMENT_CONFIG.getUdpMulticastHost();
        UDP_MULTICAST_PORT = Config.EQUIPMENT_CONFIG.getUdpMulticastPort();

        MULTICAST_GROUP_ADDRESS  = new InetSocketAddress(UDP_MULTICAST_HOST, UDP_MULTICAST_PORT);
        POINT_HANDLER = new PointUdpHandler(UDP_SERVER_LISTEN_PORT);
        MULTICAST_HANDLER = new UdpMulticastHandler(MULTICAST_GROUP_ADDRESS);
    }

    /**
     * @Description: 启动UDP 点对点的服务监听
     * @Author: ShenJinXiang
     * @return: void
     **/
    public static void runPointUdpServer() {
        if (!POINT_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new UdpServer(
                    UDP_SERVER_LISTEN_PORT,
                    POINT_HANDLER
            ));
        }
    }

    /**
     * @Description: 关闭UDP服务
     * @Author: ShenJinXiang
     * @return: void
     **/
    public static void closePointUdpServer() {
        if (POINT_HANDLER.isConn()) {
            POINT_HANDLER.close();
        }

    }

    /**
     * @Description: 启动udp组播
     * @Author: ShenJinXiang
     * @return: void
     **/
    public static void runMulticastUdpServer() {
        if (!MULTICAST_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new UdpMulticastServer(
                    MULTICAST_GROUP_ADDRESS,
                    UDP_MULTICAST_NETWOKINTERFACE,
                    MULTICAST_HANDLER
            ));
        }
    }

    /**
     * @Description: 关闭UDP组播
     * @Author: ShenJinXiang
     * @return: void
     **/
    public static void closeMulticastUdpServer() {
        if (MULTICAST_HANDLER.isConn()) {
            MULTICAST_HANDLER.close();
        }
    }

    /**
     * @Description: udp服务是否启动
     * @Author: ShenJinXiang
     * @return: boolean
     **/
    public static boolean isPointUdpConn() {
        return POINT_HANDLER.isConn();
    }

    /**
     * @Description: 组播是否启动
     * @Author: ShenJinXiang
     * @return: boolean
     **/
    public static boolean isMulticastUdpConn() {
        return MULTICAST_HANDLER.isConn();
    }

    public static void sendPointMsg(byte[] data, InetSocketAddress address) {
        POINT_HANDLER.sendMsg(data, address);
    }

    public static void sendMulticastMsg(byte[] data) {
        MULTICAST_HANDLER.sendMsg(data, MULTICAST_GROUP_ADDRESS);
    }
}
