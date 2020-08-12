package com.shenjinxiang.client.core;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.socket.DatagramPacket;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 16:17
 */
public class UdpKit {

    public static Channel CHANNEL;

    public static void sendMsg(String content, String host, int port) {
        try {
            sendMsg(content.getBytes(Config.ENCODE), new InetSocketAddress(host, port));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void sendMsg(byte[] bytes, String host, int port) {
        sendMsg(bytes, new InetSocketAddress(host, port));
    }

    public static void sendMsg(byte[] bytes, InetSocketAddress inetSocketAddress) {
        DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), inetSocketAddress);
        CHANNEL.writeAndFlush(data);
    }
}
