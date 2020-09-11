package com.shenjinxiang.exam.io;

import com.shenjinxiang.exam.kit.ByteArrayConveter;
import com.shenjinxiang.exam.kit.ByteKit;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName TcpClient
 * @Author ShenjinXiang
 * @Date 2020/9/10 0010 22:53
 */
public class TcpClient2 {

    public TcpClient2(String host, int port) {
        try {

            //初始化一个socket
            Socket socket = new Socket(host, port);
            //通过socket获取字符流
            OutputStream outputStream = socket.getOutputStream();
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String str1 = "中华人民共和国，中央人民政府";
            byte[] bytes1 = str1.getBytes(StandardCharsets.UTF_8);
            String str2 = "中华人民共和国，中央人民政府1234";
            byte[] b1 = ByteKit.asBytes(
                    ByteKit.hexStrToByteArray("eb90"),
                    ByteArrayConveter.getByteArray(bytes1.length),
                    bytes1
            );
            System.out.println(ByteKit.byteArrayToHexStr(b1));

            byte[] bytes2 = str2.getBytes(StandardCharsets.UTF_8);
            byte[] b2 = ByteKit.asBytes(
                    ByteKit.hexStrToByteArray("eb90"),
                    ByteArrayConveter.getByteArray(bytes2.length),
                    bytes2
            );
            System.out.println(ByteKit.byteArrayToHexStr(b2));

            String str3 = "中华人民共和国，中央人民政府123456789";
            byte[] bytes3 = str3.getBytes(StandardCharsets.UTF_8);
            byte[] b3 = ByteKit.asBytes(
                    ByteKit.hexStrToByteArray("eb90"),
                    ByteArrayConveter.getByteArray(bytes3.length),
                    bytes3
            );
            System.out.println(ByteKit.byteArrayToHexStr(b3));

            byte[] bytes = ByteKit.asBytes(b1, b2, b3);

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
        new TcpClient2("localhost", 9999);
    }
}
