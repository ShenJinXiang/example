package com.shenjinxiang.client;

import com.shenjinxiang.client.core.CommandReader;
import com.shenjinxiang.client.core.Config;
import com.shenjinxiang.client.core.UdpClient;
import com.shenjinxiang.client.kit.PathKit;
import com.shenjinxiang.client.kit.ThreadPool;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 16:00
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        logger.info("start...");
        if (PathKit.isJar()) {
            commandLine(args);
        }
        Config.logInfo();
        ThreadPool.getThread().execute(new UdpClient(Config.PORT));
        ThreadPool.getThread().execute(new CommandReader());
        logger.info("end...");
    }

    private static void commandLine(String[] args) throws ParseException {
        Options options = new Options();

        Option portOption = Option.builder("p").longOpt("port")
                .hasArg(true)
                .required(true)
                .type(Integer.class)
                .desc("UDP 监听的端口").build();

        Option serverHostOption = Option.builder("h").longOpt("server-host")
                .hasArg(true)
                .required(false)
                .type(String.class)
                .desc("UDP 服务端ip地址").build();


        Option serverPortOption = Option.builder("s").longOpt("server-port")
                .hasArg(true)
                .required(false)
                .type(Integer.class)
                .desc("UDP 服务端端口").build();


        options.addOption(portOption);
        options.addOption(serverHostOption);
        options.addOption(serverPortOption);

        // 解析命令行参数
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);

        if (cl.hasOption("port")) {
            String portStr = cl.getOptionValue("port");
            Config.PORT = Integer.parseInt(portStr);
        }
        if (cl.hasOption("server-host")) {
            String serverHostStr = cl.getOptionValue("server-host");
            Config.SERVER_IP = serverHostStr;
        }
        if (cl.hasOption("server-port")) {
            String serverPortStr = cl.getOptionValue("server-port");
            Config.SERVER_PORT = Integer.parseInt(serverPortStr);
        }
    }
}
