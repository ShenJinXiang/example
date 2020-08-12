package com.shenjinxiang.client.core;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 21:50
 */
public class Config {

    public static final String ENCODE = "UTF-8";
    public static final int THREAD_POOL_SIZE = 40;
    public static String SERVER_IP = "127.0.0.1";
    public static int SERVER_PORT = 8001;
    public static ChannelHandlerContext CLIENT_CONTEXT;
    public static Channel CLIENT_CHANNEL;

}
