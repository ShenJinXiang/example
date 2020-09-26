package com.shenjinxiang.rxtx.io;

import com.shenjinxiang.rxtx.kit.ByteArrayConveter;
import com.shenjinxiang.rxtx.kit.ByteKit;
import gnu.io.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 * 串口参数的配置 串口一般有如下参数可以在该串口打开以前进行配置： 包括串口号，波特率，输入/输出流控制，数据位数，停止位和奇偶校验。
 */
// 注：串口操作类一定要继承SerialPortEventListener
public class SerialPortUtils implements SerialPortEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SerialPortUtils.class);

    // 检测系统中可用的通讯端口类
    private CommPortIdentifier commPortId;
    // 枚举类型
    private Enumeration<CommPortIdentifier> portList;
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

    /**
     * 初始化串口
     *
     * @throws
     * @author LinWenLi
     * @date 2018年7月21日下午3:44:16
     * @Description: TODO
     * @param: paramConfig  存放串口连接必要参数的对象（会在下方给出类代码）
     * @return: void
     */
    @SuppressWarnings("unchecked")
    public void init(ParamConfig paramConfig) {
        // 获取系统中所有的通讯端口
        portList = CommPortIdentifier.getPortIdentifiers();
        // 记录是否含有指定串口
        boolean isExsist = false;
        // 循环通讯端口
        while (portList.hasMoreElements()) {
            commPortId = portList.nextElement();
            // 判断是否是串口
            if (commPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                // 比较串口名称是否是指定串口
                if (paramConfig.getSerialNumber().equals(commPortId.getName())) {
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
                        serialPort.setSerialPortParams(paramConfig.getBaudRate(), paramConfig.getDataBit(),
                                paramConfig.getStopBit(), paramConfig.getCheckoutBit());
                    } catch (PortInUseException e) {
                        logger.info("端口被占用");
                    } catch (TooManyListenersException e) {
                        logger.info("监听器过多");
                    } catch (UnsupportedCommOperationException e) {
                        logger.info("不支持的COMM端口操作异常");
                    }
                    // 结束循环
                    break;
                }
            }
        }
        // 若不存在该串口则抛出异常
        if (!isExsist) {
            logger.info("不存在该串口！");
        }
    }

    /**
     * 实现接口SerialPortEventListener中的方法 读取从串口中接收的数据
     */
    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
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

//                    byte[] bytes = read(serialPort);
//                    System.out.println(ByteKit.byteArrayToHexStr(bytes));
//                    print(bytes);
                    read(serialPort);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public void read(SerialPort serialPort) {
        try {
            InputStream inputStream = serialPort.getInputStream();
            byte[] bs = new byte[1];
            while (inputStream.read(bs) > 0) {
                buff.writeBytes(bs);
                if (buff.readableBytes() == 19) {
                    ByteBuf buff1 = buff.readSlice(19);
                    byte[] message = new byte[buff1.readableBytes()];
                    buff1.readBytes(message);
                    printData(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printData(byte[] bytes) {
        String hex = ByteKit.byteArrayToHexStr(bytes);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("接收到数据：");
        stringBuffer.append("\n\t字节数组：").append(Arrays.toString(bytes));
        stringBuffer.append("\n\t十六进制：").append(hex);
        String led1Str = ByteKit.getBit(bytes[4]);
        String led2Str = ByteKit.getBit(bytes[5]);
        String led3Str = ByteKit.getBit(bytes[6]);
        String led4Str = ByteKit.getBit(bytes[7]);
        String press1Str = ByteKit.getBit(bytes[8]);
        String press2Str = ByteKit.getBit(bytes[9]);
        String press3Str = ByteKit.getBit(bytes[10]);
        String press4Str = ByteKit.getBit(bytes[11]);
        byte[] bytes1 = new byte[] {0x00, 0x00, bytes[12], bytes[13]};
        byte[] bytes2 = new byte[] {0x00, 0x00, bytes[14], bytes[15]};
        stringBuffer.append("\n\tLED灯数据：")
                .append(led1Str).append("   ")
                .append(led2Str).append("    ")
                .append(led3Str).append("    ")
                .append(led4Str);
        stringBuffer.append("\n\t按键数据：")
                .append(press1Str).append("    ")
                .append(press2Str).append("    ")
                .append(press3Str).append("    ")
                .append(press4Str);
        stringBuffer.append("\n\t摇杆数据：")
                .append(ByteArrayConveter.getInt(bytes1, 0)).append("    ")
                .append(ByteArrayConveter.getInt(bytes2, 0));
        logger.info(stringBuffer.toString());
    }

    /**
     * 读取串口返回信息
     *
     * @author LinWenLi
     * @date 2018年7月21日下午3:43:04
     * @return: void
     */
    public void readComm() {
        try {
            InputStream inputStream1 = null;
            inputStream1 = serialPort.getInputStream();
//            byte[] bytes = new byte[10];
//            inputStream1.read(bytes);
            // 通过输入流对象的available方法获取数组字节长度
            int l  = inputStream1.available();
            byte[] readBuffer = new byte[l];
            // 从线路上读取数据流
            int len = 0;
            while ((len = inputStream1.read(readBuffer)) != -1) {
                // 直接获取到的数据
                data = new String(readBuffer, 0, len).trim();
                // 转为十六进制数据
                dataHex = bytesToHexString(readBuffer);
                logger.info("data:" + data);
                logger.info("dataHex:" + dataHex);// 读取后置空流对象
                inputStream1.close();
                inputStream1 = null;
                break;
            }
        } catch (IOException e) {
            logger.info("读取串口数据时发生IO异常");
        }
    }

    /**
     * 发送信息到串口
     *
     * @throws
     * @author LinWenLi
     * @date 2018年7月21日下午3:45:22
     * @param: data
     * @return: void
     */
    public void sendComm(String data) {
        byte[] writerBuffer = null;
        try {
            writerBuffer = hexToByteArray(data);
        } catch (NumberFormatException e) {
            logger.info("命令格式错误！");
        }
        try {
            outputStream = serialPort.getOutputStream();
            outputStream.write(writerBuffer);
            outputStream.flush();
        } catch (NullPointerException e) {
            logger.info("找不到串口。");
        } catch (IOException e) {
            logger.info("发送信息到串口时发生IO异常");
        }
    }

    /**
     * 关闭串口
     *
     * @throws
     * @author LinWenLi
     * @date 2018年7月21日下午3:45:43
     * @Description: 关闭串口
     * @param:
     * @return: void
     */
    public void closeSerialPort() {
        if (serialPort != null) {
            serialPort.notifyOnDataAvailable(false);
            serialPort.removeEventListener();
            if (inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    logger.info("关闭输入流时发生IO异常");
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (IOException e) {
                    logger.info("关闭输出流时发生IO异常");
                }
            }
            serialPort.close();
            serialPort = null;
        }
    }

    /**
     * 十六进制串口返回值获取
     */
    public String getDataHex() {
        String result = dataHex;
        // 置空执行结果
        dataHex = null;
        // 返回执行结果
        return result;
    }

    /**
     * 串口返回值获取
     */
    public String getData() {
        String result = data;
        // 置空执行结果
        data = null;
        // 返回执行结果
        return result;
    }

    /**
     * Hex字符串转byte
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    /**
     * hex字符串转byte数组
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            // 奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            // 偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    /**
     * 数组转换成十六进制字符串
     *
//     * @param byte[]
     * @return HexString
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        // 实例化串口操作类对象
        SerialPortUtils serialPort = new SerialPortUtils();
        // 创建串口必要参数接收类并赋值，赋值串口号，波特率，校验位，数据位，停止位
        ParamConfig paramConfig = new ParamConfig("COM2", 9600, 0, 8, 1);
        // 初始化设置,打开串口，开始监听读取串口数据
        serialPort.init(paramConfig);
    }

}