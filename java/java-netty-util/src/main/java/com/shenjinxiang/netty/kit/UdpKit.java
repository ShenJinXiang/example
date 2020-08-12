package com.shenjinxiang.netty.kit;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 15:28
 */
public class UdpKit {

    public static void sendMsg(Channel channel, byte[] bytes, String host, int port) {
        sendMsg(channel, bytes, new InetSocketAddress(host, port));
    }
    public static void sendMsg(Channel channel, byte[] bytes, InetSocketAddress inetSocketAddress) {
        DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), inetSocketAddress);
        channel.writeAndFlush(data);
    }
}
