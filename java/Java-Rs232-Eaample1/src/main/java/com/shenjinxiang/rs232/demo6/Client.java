package com.shenjinxiang.rs232.demo6;

import com.shenjinxiang.rs232.kit.ByteKit;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.transport.serial.SerialAddress;
import org.apache.mina.transport.serial.SerialAddress.Parity;
import org.apache.mina.transport.serial.SerialConnector;

public class Client {

    public static void main(String[] args) {
//        SerialConnector connector = new SerialConnector();
//        connector.setHandler(new myComHandler());//DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl
//        SerialAddress portAddress=new SerialAddress( "COM5", 2400, SerialAddress.DataBits.DATABITS_8, SerialAddress.StopBits.BITS_1, Parity.EVEN, SerialAddress.FlowControl.XONXOFF_IN,new Long(200));
//        ConnectFuture future = connector.connect(portAddress);
//        try {
//            future.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        IoSession sessin = future.getSession();
////  sessin.getFilterChain().addFirst("first",  new addfilter());
//        IoSessionConfig sc=sessin.getService().getSessionConfig();
//        sessin.setAttribute("comname", "COM5");
//        String s="1111111111111111111111111111111111111111111111111111111111111111111111";
//        s=s.replace(" ", "");
//        System.out.println(s);
//        byte[] b= ByteKit.hexStrToByteArray(s);
//        sessin.write(ByteKit.byteArrayToHexStr(b));
//        connector.dispose();
    }
}
