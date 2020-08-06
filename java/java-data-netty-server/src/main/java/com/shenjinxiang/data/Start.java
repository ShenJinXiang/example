package com.shenjinxiang.data;

import com.shenjinxiang.data.core.Consts;
import com.shenjinxiang.data.core.NettyTcpServer;
import com.shenjinxiang.data.core.SendMsgThread;
import com.shenjinxiang.data.kit.PathKit;
import com.shenjinxiang.data.kit.ThreadPool;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/4 10:04
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    private static Random random = new Random();

    public static void main(String[] args) throws ParseException {
        if (PathKit.isJar()) {
            commandLine(args);
        }
        logInfo();
        ThreadPool.getThread().execute(new NettyTcpServer(Consts.PORT));
        ThreadPool.getThread().execute(new SendMsgThread());
    }

    private static void logInfo() {
        StringBuilder stringBuilder = new StringBuilder("初始化信息: ");
        stringBuilder.append("\n\t PORT: ").append(Consts.PORT)
                .append("\n\t LENGTH: ").append(Consts.LENGTH)
                .append("\n\t MIN_VAL: ").append(Consts.MIN_VAL)
                .append("\n\t MAX_VAL: ").append(Consts.MAX_VAL)
                .append("\n\t MIN_PEAK_VAL: ").append(Consts.MIN_PEAK_VAL)
                .append("\n\t MAX_PEAK_VAL: ").append(Consts.MAX_PEAK_VAL)
                .append("\n");
        logger.info(stringBuilder.toString());
    }

    private static void commandLine(String[] args) throws ParseException {
        Options options = new Options();

        Option portOption = Option.builder("p").longOpt("port")
                .hasArg(true)
                .required(true)
                .type(Integer.class)
                .desc("TCP Server Port").build();
        Option lenOption = Option.builder("l").longOpt("length")
                .hasArg(true)
                .required(false)
                .type(Integer.class)
                .desc("data array length").build();
        Option minValOption = Option.builder("i").longOpt("minval")
                .hasArg(true)
                .required(false)
                .type(Integer.class)
                .desc("最小值").build();
        Option maxValOption = Option.builder("a").longOpt("maxval")
                .hasArg(true)
                .required(false)
                .type(Integer.class)
                .desc("最大值").build();
        Option minPeakValOption = Option.builder("ip").longOpt("minPeakVal")
                .hasArg(true)
                .required(false)
                .type(Integer.class)
                .desc("最低峰值").build();
        Option maxPeakValOption = Option.builder("ap").longOpt("maxPeakVal")
                .hasArg(true)
                .required(false)
                .type(Integer.class)
                .desc("最高峰值").build();


        options.addOption(portOption);
        options.addOption(lenOption);
        options.addOption(minValOption);
        options.addOption(maxValOption);
        options.addOption(minPeakValOption);
        options.addOption(maxPeakValOption);

        // 解析命令行参数
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);

        String portStr = cl.getOptionValue("port");
        Consts.PORT = Integer.parseInt(portStr);
        if (cl.hasOption("length") ) {
            String lenStr = cl.getOptionValue("length");
            Consts.LENGTH = Integer.parseInt(lenStr);
        }
        if (cl.hasOption("minval") ) {
            String minValStr = cl.getOptionValue("minval");
            Consts.MIN_VAL = Integer.parseInt(minValStr);
        }
        if (cl.hasOption("maxval") ) {
            String maxValStr = cl.getOptionValue("maxval");
            Consts.MAX_VAL = Integer.parseInt(maxValStr);
        }
        if (cl.hasOption("minPeakVal") ) {
            String minPeakValStr = cl.getOptionValue("minPeakVal");
            Consts.MIN_PEAK_VAL = Integer.parseInt(minPeakValStr);
        }
        if (cl.hasOption("maxPeakVal") ) {
            String maxPeakValStr = cl.getOptionValue("maxPeakVal");
            Consts.MAX_PEAK_VAL = Integer.parseInt(maxPeakValStr);
        }
    }
}
