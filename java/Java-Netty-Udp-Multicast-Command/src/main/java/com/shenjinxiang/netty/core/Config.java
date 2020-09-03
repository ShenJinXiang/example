package com.shenjinxiang.netty.core;

import io.netty.channel.ChannelHandlerContext;

public class Config {

    public static final String ENCODE = "UTF-8";
    public static String INDEX;
    public static int PORT = 8989;
    public static String HOST = "224.255.10.0";

    public static NettyMulticastHandler HANDLER;
}
