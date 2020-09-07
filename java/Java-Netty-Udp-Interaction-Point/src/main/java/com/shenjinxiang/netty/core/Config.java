package com.shenjinxiang.netty.core;

import com.shenjinxiang.netty.io.NettyMulticastHandler;
import com.shenjinxiang.netty.io.UdpHandler;

public class Config {

    public static String ENCODE = "UTF-8";

    public static int UDP_PORT;
    public static UdpHandler UDP_HANDLER;

    public static int MULTICAST_PORT = 8989;
    public static String MULTICAST_HOST = "224.255.10.0";
    public static NettyMulticastHandler MULTICAST_HANDLER;

    public static Sender SENDER = new Sender();
}
