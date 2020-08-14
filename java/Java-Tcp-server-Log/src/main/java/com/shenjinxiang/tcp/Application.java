package com.shenjinxiang.tcp;

import com.shenjinxiang.tcp.core.Config;
import com.shenjinxiang.tcp.core.NettyTcpServer;
import com.shenjinxiang.tcp.kit.ThreadPool;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/14 22:18
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws ParseException {
        logger.info("start...");
        commandLine(args);
        Config.logInfo();
        ThreadPool.getThread().execute(new NettyTcpServer(Config.PORT));
    }

    private static void commandLine(String[] args) throws ParseException {
        Options options = new Options();

        Option portOption = Option.builder("p").longOpt("port")
                .hasArg(true)
                .required(false)
                .type(Integer.class)
                .desc("UDP 监听的端口").build();

        options.addOption(portOption);

        // 解析命令行参数
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);

        if (cl.hasOption("port")) {
            String portStr = cl.getOptionValue("port");
            Config.PORT = Integer.parseInt(portStr);
        }
    }

}
