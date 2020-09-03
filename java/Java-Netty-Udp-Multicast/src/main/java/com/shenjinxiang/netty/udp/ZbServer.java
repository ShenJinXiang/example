package com.shenjinxiang.netty.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ZbServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ZbServer.class);

    private int count = 0;
    private InetAddress inetAddress;
    private MulticastSocket socket;
    private int port = 9999;

    public ZbServer(String host) {

        try {
            inetAddress = InetAddress.getByName(host);
            socket = new MulticastSocket(this.port);
            socket.setTimeToLive(1);
            socket.joinGroup(inetAddress);
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = null;
                byte[] data = ("中华人民共和国" + this.count).getBytes("UTF-8");
                packet = new DatagramPacket(data, data.length, inetAddress, port);
                logger.info("发送数据" + this.count);
                socket.send(packet);
                this.count++;

                Thread.sleep(1000);
            } catch (Exception e) {

            }

        }

    }

    public static void main(String[] args) {
        new ZbServer("224.255.10.0").run();
    }
}
