package com.shenjinxiang.rxtx.core;

import com.shenjinxiang.rxtx.rxtx.RxtxConfig;
import com.shenjinxiang.rxtx.rxtx.SerialPortServer;


public class Consts {

    public static final int THREAD_POOL_SIZE = 20;

    public static int KEY_PRESS_INTERVAL = 150;
    public static int KEY_COMMAND_INTERVAL = 100;
    public static String RXTX_SERIAL_NUMBER= "COM4";
    public static final int RXTX_BAUDRATE = 9600;
    public static final int RXTX_CHECKOUT_BIT = 0;
    public static final int RXTX_DATA_BIT = 8;
    public static final int RXTX_STOP_BIT = 1;

    public static RxtxConfig RXTX_CONFIG;
    public static SerialPortServer SERIAL_PORT_SERVER;



}
