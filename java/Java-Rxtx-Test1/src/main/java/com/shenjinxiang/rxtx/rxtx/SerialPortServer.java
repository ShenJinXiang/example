package com.shenjinxiang.rxtx.rxtx;

import com.shenjinxiang.rxtx.kit.ByteKit;
import gnu.io.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/10/5 17:21
 */
public class SerialPortServer implements SerialPortEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SerialPortServer.class);

    private RxtxConfig rxtxConfig;
    // RS232串口
    private SerialPort serialPort;
    // 输入流
    private InputStream inputStream;
    // 输出流
    private OutputStream outputStream;
    // 保存串口返回信息
    private String data;
    private ByteBuf buff = Unpooled.buffer();
    // 保存串口返回信息十六进制
    private String dataHex;

    public SerialPortServer(RxtxConfig rxtxConfig) {
        this.rxtxConfig = rxtxConfig;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
            case SerialPortEvent.BI: // 通讯中断
            case SerialPortEvent.OE: // 溢位错误
            case SerialPortEvent.FE: // 帧错误
            case SerialPortEvent.PE: // 奇偶校验错误
            case SerialPortEvent.CD: // 载波检测
            case SerialPortEvent.CTS: // 清除发送
            case SerialPortEvent.DSR: // 数据设备准备好
            case SerialPortEvent.RI: // 响铃侦测
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 输出缓冲区已清空
                break;
            case SerialPortEvent.DATA_AVAILABLE: // 有数据到达
                try {
                    read(serialPort);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public boolean start() {
        // 检测系统中可用的通讯端口类
        CommPortIdentifier commPortId;
        // 获取系统中所有的通讯端口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        // 记录是否含有指定串口
        boolean isExsist = false;
        // 循环通讯端口
        while (portList.hasMoreElements()) {
            commPortId = portList.nextElement();
            // 判断是否是串口
            if (commPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                // 比较串口名称是否是指定串口
                if (this.rxtxConfig.getSerialNumber().equals(commPortId.getName())) {
                    // 串口存在
                    isExsist = true;
                    // 打开串口
                    try {
                        // open:（应用程序名【随意命名】，阻塞时等待的毫秒数）
                        serialPort = (SerialPort) commPortId.open(Object.class.getSimpleName(), 2000);
                        // 设置串口监听
                        serialPort.addEventListener(this);
                        // 设置串口数据时间有效(可监听)
                        serialPort.notifyOnDataAvailable(true);
                        // 设置串口通讯参数:波特率，数据位，停止位,校验方式
                        serialPort.setSerialPortParams(
                                this.rxtxConfig.getBaudRate(),
                                this.rxtxConfig.getDataBit(),
                                this.rxtxConfig.getStopBit(),
                                this.rxtxConfig.getCheckoutBit()
                        );
                        return true;
                    } catch (PortInUseException e) {
                        logger.error("端口被占用", e);
                        return false;
                    } catch (TooManyListenersException e) {
                        logger.error("监听器过多", e);
                        return false;
                    } catch (UnsupportedCommOperationException e) {
                        logger.error("不支持的COMM端口操作异常", e);
                        return false;
                    }
                }
            }
        }
        // 若不存在该串口则抛出异常
        if (!isExsist) {
            logger.info("不存在该串口[" + this.rxtxConfig.getSerialNumber() + "]！");
            return false;
        }
        return false;
    }

    public boolean stop() {
        if (this.serialPort != null) {
            this.serialPort.notifyOnDataAvailable(false);
            this.serialPort.removeEventListener();
            if (this.inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    logger.error("关闭输入流时发生IO异常", e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (IOException e) {
                    logger.error("关闭输出流时发生IO异常", e);
                }
            }
            this.serialPort.close();
            this.serialPort = null;
        }
        return true;
    }


    /**
     * 发送
     * @param data
     */
    public void send(byte[] data) {
        try {
            outputStream = serialPort.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            logger.info("发送指令：" + ByteKit.byteArrayToHexStr(data));
        } catch (NullPointerException e) {
            logger.error("找不到串口", e);
        } catch (IOException e) {
            logger.error("发送信息到串口时发生IO异常", e);
        }
    }

    private void read(SerialPort serialPort) {
        try {
            InputStream inputStream = serialPort.getInputStream();
            byte[] bs = new byte[1];
            while (inputStream.read(bs) > 0) {
                buff.writeBytes(bs);
                if (buff.readableBytes() == 18) {
                    ByteBuf buff1 = buff.readSlice(18);
                    byte[] message = new byte[buff1.readableBytes()];
                    buff1.readBytes(message);
//                    System.out.println(ByteKit.byteArrayToHexStr(message));
                    RxtxReader.read(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
