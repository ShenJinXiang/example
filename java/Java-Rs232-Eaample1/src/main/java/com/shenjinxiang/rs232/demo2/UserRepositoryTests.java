package com.shenjinxiang.rs232.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UserRepositoryTests<E> {

    static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

    public static void test3() throws InterruptedException{
        List<String> portNameList = new ArrayList<String>();
        portNameList.add("COM4");
        portNameList.add("COM6");
        portNameList.add("COM8");
        try {

            /*
             * Rs232Dto dto = JacksonUtil.fromJSON(
             * "{\"opCode\":\"01H\",\"param1\":\"huilu\",\"param2\":\"www.baidu.com\"}",
             * Rs232Dto.class); System.out.println(dto);
             */

            RxtxBuilder.init(portNameList);
//            CommUtil comm4 = RxtxBuilder.comms.get(0);
//            CommUtil comm6 = RxtxBuilder.comms.get(1);
//            CommUtil comm8 = RxtxBuilder.comms.get(2);
//            while (true) {
//                String msg = queue.take();
//                if (msg.contains("4")) {
//                    if (msg.contains("H") ) {
//                        comm4.send("4fail");
//                    }else {
//                        comm4.send("4success");
//                    }
//                }
//                if (msg.contains("6")) {
//                    if (msg.contains("H") ) {
//                        comm6.send("6fail");
//                    }else {
//                        comm6.send("6success");
//                    }
//                }
//                if (msg.contains("8")) {
//                    if (msg.contains("H") ) {
//                        comm8.send("8fail");
//                    }else {
//                        comm8.send("8success");
//                    }
//                }
//
//                Thread.sleep(100);
//            }
        } finally {
//
//            //comm4.ClosePort(); comm6.ClosePort(); comm8.ClosePort();
//
        }
    }

    public static void main(String[] args) throws InterruptedException {
        test3();
    }
}
