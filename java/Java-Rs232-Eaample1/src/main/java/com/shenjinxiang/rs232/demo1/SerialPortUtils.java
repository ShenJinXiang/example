package com.shenjinxiang.rs232.demo1;

import com.shenjinxiang.rs232.kit.ByteArrayConveter;
import com.shenjinxiang.rs232.kit.ByteKit;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 * 串口参数的配置 串口一般有如下参数可以在该串口打开以前进行配置： 包括串口号，波特率，输入/输出流控制，数据位数，停止位和奇偶校验。
 */
// 注：串口操作类一定要继承SerialPortEventListener
public class SerialPortUtils implements SerialPortEventListener {
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
                        System.out.println("端口被占用");
                    } catch (TooManyListenersException e) {
                        System.out.println("监听器过多");
                    } catch (UnsupportedCommOperationException e) {
                        System.out.println("不支持的COMM端口操作异常");
                    }
                    // 结束循环
                    break;
                }
            }
        }
        // 若不存在该串口则抛出异常
        if (!isExsist) {
            System.out.println("不存在该串口！");
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
                // 调用读取数据的方法
//                readComm();
                try {

//                    byte[] bytes = read(serialPort);
//                    System.out.println(ByteKit.byteArrayToHexStr(bytes));
//                    print(bytes);
                    read2601(serialPort);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void print(byte[] bytes) {
        byte led1 = bytes[4];
        byte led2 = bytes[5];
        byte led3 = bytes[6];
        byte led4 = bytes[7];

        byte press1 = bytes[8];
        byte press2 = bytes[9];
        byte press3 = bytes[10];
        byte press4 = bytes[11];

        String led1Str = getBit(led1);
        String led2Str = getBit(led2);
        String led3Str = getBit(led3);
        String led4Str = getBit(led4);
        String press1Str = getBit(press1);
        String press2Str = getBit(press2);
        String press3Str = getBit(press3);
        String press4Str = getBit(press4);
        byte[] bytes1 = new byte[] {0x00, 0x00, bytes[12], bytes[13]};
        byte[] bytes2 = new byte[] {0x00, 0x00, bytes[14], bytes[15]};
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(led1Str).append("   ")
                .append(led2Str).append("    ")
                .append(led3Str).append("    ")
                .append(led4Str).append("    ")
                .append(press1Str).append("    ")
                .append(press2Str).append("    ")
                .append(press3Str).append("    ")
                .append(press4Str).append("    ")
                .append(ByteArrayConveter.getInt(bytes1, 0)).append("    ")
                .append(ByteArrayConveter.getInt(bytes2, 0));
        System.out.println(stringBuilder.toString());
    }

    public static byte[] read1(SerialPort serialPort) {
        try {
            InputStream inputStream = null;
            ByteBuf buff = Unpooled.buffer();
            DataInputStream ins = new DataInputStream(serialPort.getInputStream());
            byte b = 0;
            while (true) {
                b = (byte) ins.read();
                buff.writeByte(b);
                if(b == 0) {
                    break;
                }
            }
            byte[] message = new byte[buff.readableBytes()];
            buff.readBytes(message);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] readData(SerialPort serialPort) {
        try {

            ByteBuf buff = Unpooled.buffer();
            InputStream inputStream = serialPort.getInputStream();
            int bufflenth = inputStream.available();
            while (bufflenth > 0) {
                byte[] bytes = new byte[bufflenth];
                inputStream.read(bytes);
                buff.writeBytes(bytes);
                bufflenth = inputStream.available();
            }
            byte[] message = new byte[buff.readableBytes()];
            buff.readBytes(message);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] read(SerialPort serialPort) {
        try {
            InputStream inputStream = serialPort.getInputStream();
            byte[] bs = new byte[1];
            int len;
            while ((len = inputStream.read(bs)) > 0) {
                buff.writeBytes(bs);
            }
            byte[] message = new byte[buff.readableBytes()];
            buff.readBytes(message);
            System.out.println(Arrays.toString(message));
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void read2601(SerialPort serialPort) {
        try {
            InputStream inputStream = serialPort.getInputStream();
            byte[] bs = new byte[1];
            int len;
            while ((len = inputStream.read(bs)) > 0) {
                buff.writeBytes(bs);
                if (buff.readableBytes() == 19) {
                    ByteBuf buff1 = buff.readSlice(19);
                    byte[] message = new byte[buff1.readableBytes()];
                    buff1.readBytes(message);
                    System.out.println(Arrays.toString(message));
                    print(message);
                }
            }
//            byte[] message = new byte[buff.readableBytes()];
//            buff.readBytes(message);
//            System.out.println(Arrays.toString(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readFromPort1(SerialPort serialPort) {

        InputStream in = null;
        byte[] bytes = null;

        try {

            in = serialPort.getInputStream();
            int bufflenth = in.available(); //获取buffer里的数据长度

            while (bufflenth != 0) {
                bytes = new byte[bufflenth]; //初始化byte数组为buffer中数据的长度
                in.read(bytes);
                bufflenth = in.available();
            }
        } catch (IOException e) {
//            throw new ReadDataFromSerialPortFailure();
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch(IOException e) {
//                throw new SerialPortInputStreamCloseFailure();
                e.printStackTrace();
            }

        }
        System.out.println("读取成功");
        return bytes;

    }

    public static byte[] readFromPort2(SerialPort serialPort) throws Exception {

        InputStream in = null;
        byte[] bytes = null;

        try {

            in = serialPort.getInputStream();
            int bufflenth = in.available(); //获取buffer里的数据长度

            while (bufflenth != 0) {
                bytes = new byte[bufflenth]; //初始化byte数组为buffer中数据的长度
                in.read(bytes);
                bufflenth = in.available();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch(IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("读取成功");
        return bytes;

    }



    public static byte[] readFromPort(SerialPort serialPort) {
//        ByteBuf buffer = Unpooled.buffer();
//        for (byte[] byteArr: bytes) {
//            buffer.writeBytes(byteArr);
//        }
//        byte[] message = new byte[buffer.readableBytes()];
//        buffer.readBytes(message);
//        return message;

        InputStream in = null;
        byte[] bytes = {};
        ByteBuf buffer = Unpooled.buffer();
        try {
            in = serialPort.getInputStream();
            // 缓冲区大小为一个字节
            byte[] readBuffer = new byte[1];
            int bytesNum = 1;
            int n = 0;
            while (bytesNum > 0) {
                //如果缓冲区读取结束，且数组长度为7（防止串口数据发送缓慢读取到空数据），跳出循环
                n = in.available();
                if (n >0 && n != 7) {
                    bytesNum = in.read(readBuffer);
                    buffer.writeBytes(readBuffer);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
//            new ReadDataFromSerialPortFailure().printStackTrace();
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
//                new SerialPortInputStreamCloseFailure().printStackTrace();
                e.printStackTrace();
            }
        }
        byte[] message = new byte[buffer.readableBytes()];
        buffer.readBytes(message);
        return message;
//        return bytes;
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
                System.out.println("data:" + data);
                System.out.println("dataHex:" + dataHex);// 读取后置空流对象
                inputStream1.close();
                inputStream1 = null;
                break;
            }
        } catch (IOException e) {
            System.out.println("读取串口数据时发生IO异常");
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
            System.out.println("命令格式错误！");
        }
        try {
            outputStream = serialPort.getOutputStream();
            outputStream.write(writerBuffer);
            outputStream.flush();
        } catch (NullPointerException e) {
            System.out.println("找不到串口。");
        } catch (IOException e) {
            System.out.println("发送信息到串口时发生IO异常");
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
                    System.out.println("关闭输入流时发生IO异常");
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (IOException e) {
                    System.out.println("关闭输出流时发生IO异常");
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

    public static String getBit(byte by) {
        StringBuffer sb = new StringBuffer();
        sb.append((by>>7)&0x1)
                .append((by>>6)&0x1)
                .append((by>>5)&0x1)
                .append((by>>4)&0x1)
                .append((by>>3)&0x1)
                .append((by>>2)&0x1)
                .append((by>>1)&0x1)
                .append((by>>0)&0x1);
        return sb.toString();
    }

    public static byte bitToByte(String bit) {
        int re, len;
        if (null == bit) {
            return 0;
        }
        len = bit.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        if (len == 8) {// 8 bit处理
            if (bit.charAt(0) == '0') {// 正数
                re = Integer.parseInt(bit, 2);
            } else {// 负数
                re = Integer.parseInt(bit, 2) - 256;
            }
        } else {//4 bit处理
            re = Integer.parseInt(bit, 2);
        }
        return (byte) re;
    }


    public static void main(String[] args) {
        // 实例化串口操作类对象
        SerialPortUtils serialPort = new SerialPortUtils();
        // 创建串口必要参数接收类并赋值，赋值串口号，波特率，校验位，数据位，停止位
        ParamConfig paramConfig = new ParamConfig("COM3", 9600, 0, 8, 1);
        // 初始化设置,打开串口，开始监听读取串口数据
        serialPort.init(paramConfig);


//        String str = "社会主义核心价值观是社会主义核心价值体系的内核";
//        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
//        String hex = ByteKit.byteArrayToHexStr(bytes);
//        hex = "AA0218ADF0000000FFFFFF7F00000000FF550000000000000000000";
//        for (int i =0; i < 100; i++) {
//            serialPort.sendComm(hex);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        // 调用串口操作类的sendComm方法发送数据到串口
//        String com = "AA0218ADF0000000FFFFFF7F00000000FF55";
//        String com = "AA0218AD01000000FFFFFF7F00000000EE55";
//        serialPort.sendComm(com);
        // AA 02 19 AD 00 00 00 00 FE FF FF 7F 07 97 07 B2 44 55 01
//         AA 02 19 AD 00 00 00 00 FE FF FF 7F 07 96 07 B0 41 55 01
        // 关闭串口
//        serialPort.closeSerialPort();
//        String com = "AA0218AD00400000FFFFFF7F000000002D55";
//        serialPort.sendComm(com);


        // AA0219AD00000000FEFFFF7F079107B7435501
//        int a = 0xAA + 0x02 + 0x19 + 0xAD + 0x00 + 0x00 + 0x00 + 0x00 + 0xFE + 0xFF + 0xFF + 0x7F +0x07 + 0x91 + 0x07 + 0xB7; // + 0x43 ;//5501
        // AA0218AD00400000FFFFFF7F000000002D55
//        a = 0xAA + 0x02 + 0x18 + 0xAD + 0x00 + 0x40 + 0x00 + 0x00 + 0xFF + 0xFF + 0xFF + 0x7F + 0x00 + 0x00 + 0x00 + 0x00; // 2D55
//        System.out.println(a);
//        byte[] bytes = ByteArrayConveter.getByteArray(a);
//        System.out.println(Arrays.toString(bytes));
//        System.out.println(ByteKit.byteArrayToHexStr(bytes));
    }

    
}