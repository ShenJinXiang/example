package com.shenjinxiang.tcp;

import java.io.*;
import java.net.Socket;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/29 11:12
 */
public class TcpClient {


    public TcpClient(String host, int port) {
        try {

            //初始化一个socket
            Socket socket = new Socket(host, port);
            //通过socket获取字符流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //通过标准输入流获取字符流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
            String str;
            do {
                str = bufferedReader.readLine();
                bufferedWriter.write(str);
                bufferedWriter.write("\n");
                bufferedWriter.flush();
            } while (!"exit".equals(str));
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
