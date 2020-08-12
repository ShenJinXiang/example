package com.shenjinxiang.netty;

import com.shenjinxiang.netty.kit.*;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/1 20:44
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) throws ParseException {
        logger.info("start...");
//        Options options = new Options();
//        Option portOpt = Option.builder("p").longOpt("port")
//                .hasArg(true)
//                .required(false)
//                .type(Integer.class)
//                .desc("监听的服务端端口")
//                .build();
//        options.addOption(portOpt);
//        CommandLineParser parser = new DefaultParser();
//        CommandLine cl = parser.parse(options, args);
//
//        String portStr = cl.getOptionValue("port");
//        NettyTcpServer server = new NettyTcpServer(Integer.parseInt(portStr));
//        ThreadPool.getThread().execute(server);

//        ThreadPool.getThread().execute(new NettyTcpClient("127.0.0.1", 6001));
        new NettyUdpClient(3333);
        String str = "山西太原申锦祥";
        try {
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 5005);
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < 100; i++) {
                UdpKit.sendMsg(Consts.CHANNEL, bytes, address);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("end...");
    }
}
