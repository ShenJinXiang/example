package com.shenjinxiang.rxtx;

import com.shenjinxiang.rxtx.io.CommandReader;
import com.shenjinxiang.rxtx.io.ParamConfig;
import com.shenjinxiang.rxtx.io.SerialPortUtils;
import com.shenjinxiang.rxtx.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Java-Rxtx-Kit1 Start ...");
        // 实例化串口操作类对象
//        SerialPortUtils serialPort = new SerialPortUtils();
//        // 创建串口必要参数接收类并赋值，赋值串口号，波特率，校验位，数据位，停止位
//        ParamConfig paramConfig = new ParamConfig("COM2", 9600, 0, 8, 1);
//        // 初始化设置,打开串口，开始监听读取串口数据
//        serialPort.init(paramConfig);

        ThreadPool.getThread().execute(new CommandReader());
    }
}
