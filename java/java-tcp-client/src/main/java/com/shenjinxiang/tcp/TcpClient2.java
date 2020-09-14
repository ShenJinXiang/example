package com.shenjinxiang.tcp;

import java.io.*;
import java.net.Socket;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/29 11:12
 */
public class TcpClient2 {


    public TcpClient2(String host, int port) {
        try {

            //初始化一个socket
            Socket socket = new Socket(host, port);

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
//            String str = "00000014000101380103020403010402000300000000000000000000000000000000";
//            String str = "00000019000101380103020403010402000300000000000000000000000000000000";
            String str = "00000004000101380103020403010402000300000000000000000000000000000000";
            byte[] bytes = ByteKit.hexStrToByteArray(str);
//            outputStream.write(str.getBytes());
            outputStream.write(bytes);
            outputStream.flush();
            byte[] bytes1 = new byte[4];
            int len = 0;
            while ((len = inputStream.read(bytes1)) != -1) {
                System.out.println(ByteKit.byteArrayToHexStr(bytes1));
            }
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TcpClient2("localhost", 6000);
    }
}
