package com.shenjinxiang.rs232.demo2;

import com.shenjinxiang.rs232.kit.ByteKit;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

public class CommUtil implements SerialPortEventListener {

    InputStream inputStream; // 从串口来的输入流
    OutputStream outputStream;// 向串口输出的流
    SerialPort serialPort; // 串口的引用
    CommPortIdentifier portId;
    int count = 0;

    public CommUtil(CommPortIdentifier temp ,String name) {
        try {
            portId = temp;
            serialPort = (SerialPort) portId.open("My" + name, 2000);
        } catch (PortInUseException e) {

        }
        try {
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
        } catch (IOException e) {
        }
        try {
            serialPort.addEventListener(this); // 给当前串口添加一个监听器
        } catch (TooManyListenersException e) {
        }
        serialPort.notifyOnDataAvailable(true); // 当有数据时通知
        try {
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, // 设置串口读写参数
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {
        }
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;

            case SerialPortEvent.DATA_AVAILABLE:// 当有可用数据时读取数据,并且给串口返回数据
                byte[] readBuffer = new byte[20];

                try {
                    while (inputStream.available() > 0) {
                        //System.out.println("data size---"+inputStream.available());
                        int numBytes = inputStream.read(readBuffer);
                        //System.out.println("data---"+numBytes);
                    }
                    //System.out.println("content-"+ count++ +"--"+new String(readBuffer).trim());
                    String req = new String(readBuffer);
                    System.out.println(ByteKit.byteArrayToHexStr(readBuffer));
                    System.out.println(Thread.currentThread()+"---"+new String(readBuffer).trim());
                    UserRepositoryTests.queue.offer(req);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void send(String content) {
        try {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ClosePort() {
        if (serialPort != null) {
            serialPort.close();
        }
    }
}
