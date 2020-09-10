package com.shenjinxiang.exam.io;

import com.shenjinxiang.exam.kit.ByteArrayConveter;
import com.shenjinxiang.exam.kit.ByteKit;

import java.io.*;
import java.net.Socket;

/**
 * @ClassName TcpClient
 * @Author ShenjinXiang
 * @Date 2020/9/10 0010 22:53
 */
public class TcpClient {

    public TcpClient(String host, int port) {
        try {

            //初始化一个socket
            Socket socket = new Socket(host, port);
            //通过socket获取字符流
            OutputStream outputStream = socket.getOutputStream();
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            byte[] bytes = ByteKit.asBytes(
                    ByteArrayConveter.getByteArray(10),
                    ByteArrayConveter.getByteArray(100),
                    ByteArrayConveter.getByteArray(201),
                    ByteArrayConveter.getByteArray(303),
                    ByteArrayConveter.getByteArray(503),
                    ByteArrayConveter.getByteArray(603),
                    ByteArrayConveter.getByteArray(703),
                    ByteArrayConveter.getByteArray(803),
                    ByteArrayConveter.getByteArray(903),
                    ByteArrayConveter.getByteArray(1003),
                    ByteArrayConveter.getByteArray(1013),
                    ByteArrayConveter.getByteArray(1023),
                    ByteArrayConveter.getByteArray(1033),
                    ByteArrayConveter.getByteArray(1043),
                    ByteArrayConveter.getByteArray(1053),
                    ByteArrayConveter.getByteArray(1063),
                    ByteArrayConveter.getByteArray(1073),
                    ByteArrayConveter.getByteArray(1083),
                    ByteArrayConveter.getByteArray(1093)
            );
            System.out.println(ByteKit.byteArrayToHexStr(bytes));
            outputStream.write(bytes);
            outputStream.flush();
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TcpClient("localhost", 9999);
    }
}
