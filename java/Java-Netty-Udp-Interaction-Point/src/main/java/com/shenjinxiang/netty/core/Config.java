package com.shenjinxiang.netty.core;

import com.shenjinxiang.netty.entity.PointConfig;
import com.shenjinxiang.netty.io.NettyMulticastHandler;
import com.shenjinxiang.netty.io.UdpHandler;

public class Config {

    public static String ENCODE = "UTF-8";

    public static UdpHandler UDP_HANDLER;
    public static NettyMulticastHandler MULTICAST_HANDLER;

    public static PointConfig POINT_CONFIG;

    public static Sender SENDER = new Sender();
}
