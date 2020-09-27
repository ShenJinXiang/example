package com.shenjinxiang.rxtx.io;

import com.shenjinxiang.rxtx.kit.ThreadPool;

public class IOKit {

    private static final SerialPortUtils SERIALPORT = new SerialPortUtils();
    private static boolean conn = false;

    public static void runRxtx(String serialNumber) {
        if (!conn) {
            ParamConfig paramConfig = new ParamConfig(serialNumber, 9600, 0, 8, 1);
            conn = SERIALPORT.init(paramConfig);
        }
    }

    public static void close() {
        if (conn) {
            SERIALPORT.closeSerialPort();
            conn = false;
        }
    }

    public static void sendData(String data) {
        if (conn) {
            SERIALPORT.sendComm(data);
        }
    }

}
