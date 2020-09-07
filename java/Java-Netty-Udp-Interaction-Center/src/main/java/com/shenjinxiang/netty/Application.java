package com.shenjinxiang.netty;

import com.shenjinxiang.netty.core.Config;
import com.shenjinxiang.netty.io.CommandReader;
import com.shenjinxiang.netty.io.NettyUdp;
import com.shenjinxiang.netty.kit.ThreadPool;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
        logger.info("start...");
        commandLine(args);
        ThreadPool.getThread().execute(Config.SENDER);
        ThreadPool.getThread().execute(new CommandReader());
    }

    private static void commandLine(String[] args) throws ParseException {
        Options options = new Options();

        Option portOption = Option.builder("p").longOpt("port")
                .hasArg(true)
                .required(true)
                .type(Integer.class)
                .desc("UDP 监听的端口").build();


        options.addOption(portOption);

        // 解析命令行参数
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);

        if (cl.hasOption("port")) {
            String portStr = cl.getOptionValue("port");
            Config.UDP_PORT = Integer.parseInt(portStr);
        }
    }
}
