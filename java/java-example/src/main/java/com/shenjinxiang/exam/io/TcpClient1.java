package com.shenjinxiang.exam.io;

import com.shenjinxiang.exam.kit.ByteArrayConveter;
import com.shenjinxiang.exam.kit.ByteKit;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @ClassName TcpClient
 * @Author ShenjinXiang
 * @Date 2020/9/10 0010 22:53
 */
public class TcpClient1 {

    public TcpClient1(String host, int port, String name) {
        try {
            int count = 1;

            //初始化一个socket
            Socket socket = new Socket(host, port);
            //通过socket获取字符流
//            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (count < 10) {
                System.out.println("发送" + count);
                bufferedWriter.write(name + " -> 中华人民国和国[" + count + "]\n");
                bufferedWriter.flush();
                count++;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            socket.shutdownOutput();
            socket.close();
            System.out.println("end");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TcpClient1("localhost", 9999, "client1");
        new TcpClient1("localhost", 9999, "client2");
        new TcpClient1("localhost", 9999, "client3");
        new TcpClient1("localhost", 9999, "client4");
    }
}
