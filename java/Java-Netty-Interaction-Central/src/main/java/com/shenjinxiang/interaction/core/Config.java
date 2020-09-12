package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.io.tcp.handler.ArTcpHandler;
import com.shenjinxiang.interaction.io.tcp.handler.TcpHandler;

/**
 * @ClassName Const
 * @Author ShenjinXiang
 * @Date 2020/9/11 23:01
 */
public class Config {

    public static final int MAX_TCP_DATA_LENGTH = 1024 * 1024;

    /**
     * QT TCP 服务监听端口
     */
    public static final int QT_SERVER_LISTEN_PORT = 6001;

    /**
     * AR TCP 连接配置
     */
    public static final String AR_SERVER_HOST = "127.0.0.1";
    public static final int AR_SERVER_PORT = 6002;

    /**
     * Alg TCP 连接配置
     */
    public static final String ALG_SERVER_HOST = "127.0.0.1";
    public static final int ALG_SERVER_PORT = 6000;
    public static final int ALG_FIXED_DATA_LENGTH = 4096;

    /**
     * UDP 监听端口
     */
    public static final int UDP_SERVER_LISTEN_PORT = 5001;

    /**
     * UDP 组播配置
     */
    public static final String UDP_MULTICAST_NETWOKINTERFACE = "lo";
    public static final String UDP_MULTICAST_HOST = "224.255.10.0";
    public static final int UDP_MULTICAST_PORT = 5000;

}
