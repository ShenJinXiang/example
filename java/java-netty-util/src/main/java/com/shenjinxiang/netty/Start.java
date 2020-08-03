package com.shenjinxiang.netty;

import com.shenjinxiang.netty.kit.NettyTcpClient;
import com.shenjinxiang.netty.kit.NettyTcpServer;
import com.shenjinxiang.netty.kit.ThreadPool;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        ThreadPool.getThread().execute(new NettyTcpClient("127.0.0.1", 6001));
        logger.info("end...");
    }
}
