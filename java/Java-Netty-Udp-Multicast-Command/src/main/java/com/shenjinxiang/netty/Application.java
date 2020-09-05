package com.shenjinxiang.netty;


import com.shenjinxiang.netty.core.CommandReader;
import com.shenjinxiang.netty.core.Config;
import com.shenjinxiang.netty.core.NettyMulticastUdp;
import com.shenjinxiang.netty.kit.ThreadPool;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import static java.awt.SystemColor.info;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        logger.info("Start...");
        commandLine(args);
        logger.info("当前程序编号为：" + Config.INDEX);
        ThreadPool.getThread().execute(new CommandReader());

//        info();




    }

    private static void info() throws Exception {
        Enumeration<NetworkInterface> nifs = null;
        nifs = NetworkInterface.getNetworkInterfaces();
        while (nifs.hasMoreElements()) {
            NetworkInterface ni = nifs.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                InetAddress addr = address.nextElement();
                if (addr instanceof Inet4Address) {
                    System.out.println("网络接口名称为：" + ni.getName());
                    System.out.println("网卡接口地址：" + addr.getHostAddress());
                    System.out.println();
                }
            }
        }
    }

    private static void commandLine(String[] args) throws ParseException {
        Options options = new Options();

        Option indexOption = Option.builder("i").longOpt("index")
                .hasArg(true)
                .required(true)
                .type(String.class)
                .desc("编号").build();
        options.addOption(indexOption);

        // 解析命令行参数
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);

        if (cl.hasOption("index")) {
            String index = cl.getOptionValue("index");
            Config.INDEX = index;
        }
    }
}
