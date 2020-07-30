package com.shenjinxiang.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/30 16:11
 */
public class UdpClient {

    private static final Logger logger = LoggerFactory.getLogger(UdpClient.class);

    private int port;
    private DatagramSocket datagramSocket;

    public UdpClient(int port) throws SocketException {
        this.port = port;
        datagramSocket = new DatagramSocket(this.port);
    }

    public void run(String ip, int port) throws IOException {
        InetAddress serverAddress = InetAddress.getByName(ip);
        DatagramPacket datagramPacket = null;
        String str = "1123123";
        byte[] bytes = str.getBytes();
        datagramPacket = new DatagramPacket(bytes,
                bytes.length,
                serverAddress,
                port);
        datagramSocket.send(datagramPacket);

        datagramSocket.receive(datagramPacket);
        byte[] bytes1 = datagramPacket.getData();
        logger.info("content: " + new String(bytes1, 0, datagramPacket.getLength()));
    }
}
