package com.shenjinxiang.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/30 16:11
 */
public class UdpServer {

    private static final Logger logger = LoggerFactory.getLogger(UdpServer.class);

    private int port;

    public UdpServer(int port) {
        this.port = port;
    }

    public void run() throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(this.port);
        logger.info("UDP服务器已启动，端口: " + this.port);
        while (true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
                datagramSocket.receive(datagramPacket);
                InetAddress address = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                byte[] bytes = datagramPacket.getData();
                String msg = new String(bytes, 0, datagramPacket.getLength());
                logger.info(String.format("端口[%d]接收到来自[%s:%d]发送的数据包\t长度: %d",
                        this.port, address, port, datagramPacket.getLength()));
                logger.info("content: " + msg);

                datagramPacket.setData("哈哈哈".getBytes());
                datagramSocket.send(datagramPacket);


            } catch (Exception e) {
                logger.error("端口[" + this.port + "]接收数据发生错误");
            }
        }
    }
}
