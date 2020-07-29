package com.shenjinxiang.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/29 10:45
 */
public class ServerThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ServerThread.class);

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket =socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = this.socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] buff = new byte[1024];
            byte b = (byte)0XEB;
            while ((len = bufferedInputStream.read(buff)) != -1) {

                byteArrayOutputStream.write(buff, 0, len);
            }
            String str = new String(byteArrayOutputStream.toByteArray());
            logger.info("接收到消息: " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
