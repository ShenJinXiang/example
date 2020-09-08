package com.shenjinxiang.netty.core;

import com.shenjinxiang.netty.entity.CenterConfig;
import com.shenjinxiang.netty.io.NettyMulticastHandler;
import com.shenjinxiang.netty.io.UdpHandler;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public static String ENCODE = "UTF-8";

    public static UdpHandler UDP_HANDLER;
    public static NettyMulticastHandler MULTICAST_HANDLER;

    public static Sender SENDER = new Sender();

//    public static boolean INIT_PORTS = false;
//    public static final List<InetSocketAddress> ADDRESS_LIST = new ArrayList<>();
    public static CenterConfig CENTER_CONFIG;
}
