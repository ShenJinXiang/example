package com.shenjinxiang.netty.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ZbClient implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ZbClient.class);

    private int port = 9999;
    private InetAddress groupAddress = null;
    private MulticastSocket multicastSocket = null;

    public ZbClient(String host) {
        try {
            groupAddress = InetAddress.getByName(host);
            multicastSocket = new MulticastSocket(this.port);
            multicastSocket.joinGroup(groupAddress);
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] data = new byte[1024];
                DatagramPacket packet = null;
                packet = new DatagramPacket(data, data.length, groupAddress, port);
                multicastSocket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("接收内容：" + msg);
                Thread.sleep(0);
            } catch (Exception e) {

            }

        }
    }

    public static void main(String[] args) {
        new ZbClient("224.255.10.0").run();
    }
}
