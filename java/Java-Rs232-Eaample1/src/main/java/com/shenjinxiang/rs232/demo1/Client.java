package com.shenjinxiang.rs232.demo1;

import com.shenjinxiang.rs232.kit.ByteKit;

import java.nio.charset.StandardCharsets;

public class Client {

    // mvn install:install-file -DgroupId=com.sj.rxtx.comm -DartifactId=rxtxcomm -Dversion=2.2.0 -Dpackaging=jar -Dfile=E:\shenjinxiang\work\radar\串口工具\mfz-rxtx-2.2-20081207-win-x64\RXTXcomm.jar

    public static void main(String[] args) {
        SerialPortUtils serialPort = new SerialPortUtils();
        // 创建串口必要参数接收类并赋值，赋值串口号，波特率，校验位，数据位，停止位
        ParamConfig paramConfig = new ParamConfig("COM4", 9600, 0, 8, 1);
        // 初始化设置,打开串口，开始监听读取串口数据
        serialPort.init(paramConfig);


        String str = "社会主义核心价值观是社会主义核心价值体系的内核";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        String hex = ByteKit.byteArrayToHexStr(bytes);
        hex = "AA0218ADF0000000FFFFFF7F00000000FF55111111111111";
        for (int i =0; i < 100; i++) {
            serialPort.sendComm(hex);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
