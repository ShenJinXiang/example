package com.shenjinxiang.rs232.demo1;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier portId;
        while (en.hasMoreElements()) {
            portId = (CommPortIdentifier) en.nextElement();
            // 如果端口类型是串口，则打印出其端口信息
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(portId.getName());
            }
        }
    }

}
