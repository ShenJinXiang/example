package com.shenjinxiang.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/29 10:13
 */
public class TcpServer {

    private static final Logger logger = LoggerFactory.getLogger(TcpServer.class);

    public TcpServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        logger.info("端口[" + port + "]等待连接。。。");
        while (true) {
            Socket socket = serverSocket.accept();
            InetAddress inetAddress = socket.getInetAddress();
            logger.info("[" + inetAddress.getHostAddress() + "]连接成功!");
            ThreadPool.getThread().execute(new ServerThread(socket));
        }
    }
}
