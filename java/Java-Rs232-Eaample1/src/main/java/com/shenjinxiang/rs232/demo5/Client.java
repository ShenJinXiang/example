package com.shenjinxiang.rs232.demo5;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.serial.SerialAddress;
import org.apache.mina.transport.serial.SerialConnector;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        IoConnector connector = new SerialConnector();

        //添加数据解析Filter
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory()));

        //添加事件处理handler
//        connector.setHandler(new SerialHandler());
        connector.setHandler(new IoHandlerAdapter());

        SerialAddress portAddress = new SerialAddress("COM1", 9600, SerialAddress.DataBits.DATABITS_8, SerialAddress.StopBits.BITS_1, SerialAddress.Parity.NONE, SerialAddress.FlowControl.NONE);

        ConnectFuture future = connector.connect(portAddress);
        future.await();
//        IoSession session = future.getSession();
//        session.write("00");
    }
}
