package com.shenjinxiang.client;

import com.shenjinxiang.client.core.Config;
import com.shenjinxiang.client.io.CommandReader;
import com.shenjinxiang.client.io.NettyTcpClient;
import com.shenjinxiang.client.kit.PathKit;
import com.shenjinxiang.client.kit.ThreadPool;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 21:47
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
        if (PathKit.isJar()) {
            commandLine(args);
        }
        logInfo();
        ThreadPool.getThread().execute(new NettyTcpClient(Config.SERVER_IP, Config.SERVER_PORT));
        ThreadPool.getThread().execute(new CommandReader());
    }

    private static void logInfo() {
        StringBuilder stringBuilder = new StringBuilder("初始化信息: ");
        stringBuilder.append("\n\t Host: ").append(Config.SERVER_IP)
                .append("\n\t Port: ").append(Config.SERVER_PORT)
                .append("\n");
        logger.info(stringBuilder.toString());
    }

    private static void commandLine(String[] args) throws ParseException {
        Options options = new Options();

        Option ipOption = Option.builder("h").longOpt("host")
                .hasArg(true)
                .required(true)
                .type(Integer.class)
                .desc("TCP Server host").build();

        Option portOption = Option.builder("p").longOpt("port")
                .hasArg(true)
                .required(true)
                .type(Integer.class)
                .desc("TCP Server Port").build();

        options.addOption(ipOption);
        options.addOption(portOption);

        // 解析命令行参数
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);

        String ipStr = cl.getOptionValue("host");
        Config.SERVER_IP = ipStr;
        String portStr = cl.getOptionValue("port");
        Config.SERVER_PORT = Integer.parseInt(portStr);
    }
}
