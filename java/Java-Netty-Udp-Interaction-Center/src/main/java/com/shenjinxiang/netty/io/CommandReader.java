package com.shenjinxiang.netty.io;

import com.shenjinxiang.netty.core.Config;
import com.shenjinxiang.netty.kit.StrKit;
import com.shenjinxiang.netty.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 22:00
 */
public class CommandReader implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private static final String SEND_PREFIX = "send";
    private static final String START = "start";
    private static final String END = "end";
    private static final String EXIT = "exit";
    private static final String CLOSE = "close";
    private static final String CONN = "conn";

    private BufferedReader bufferedReader;

    public CommandReader() throws UnsupportedEncodingException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in, Config.ENCODE));
        try {
            System.out.println("输入设备udp监听端口，逗号分隔");
            String line = bufferedReader.readLine();
            String[] ps = line.split(",");
            for(String p: ps) {
                int port = Integer.parseInt(p.trim());
                Config.ADDRESS_LIST.add(new InetSocketAddress("127.0.0.1", port));
            }
            Config.INIT_PORTS = true;
        } catch (Exception e) {
            logger.error("命令行启动出错", e);
        }
    }

    @Override
    public void run() {
        while (true) {
            logger.info("请输入命令: ");
            try {
                String line = bufferedReader.readLine();
                if (StrKit.notBlank(line) && !"".equals(line.trim())) {
                    logger.info("录入内容：" + line);
                    String[] words = line.trim().split("\\s+");
                    if (CONN.equalsIgnoreCase(words[0])) {
                        if (words.length < 2 || StrKit.isBlank(words[1])) {
                            logger.info("请指定网卡名称:");
                            info();
                            continue;
                        }
                        // 加入组播
                        if (null != Config.MULTICAST_HANDLER && Config.MULTICAST_HANDLER.isConn()) {
                            Config.MULTICAST_HANDLER.close();
                        }
                        String networkInterfaceName = words[1];
                        InetSocketAddress groupAddress = new InetSocketAddress(Config.MULTICAST_HOST, Config.MULTICAST_PORT);
                        ThreadPool.getThread().execute(new NettyMulticastUdp(groupAddress, networkInterfaceName));

                        // 启动udp
                        if (null != Config.UDP_HANDLER && Config.UDP_HANDLER.isConn()) {
                            Config.UDP_HANDLER.close();
                        }
                        ThreadPool.getThread().execute(new NettyUdp(Config.UDP_PORT));
                        Thread.sleep(3000);
                    }
                    if (CLOSE.equalsIgnoreCase(words[0])) {
                        Config.UDP_HANDLER.close();
                        Config.MULTICAST_HANDLER.close();
                    }
                    if (START.equalsIgnoreCase(words[0])) {
                        if (!Config.UDP_HANDLER.isConn()) {
                            logger.info("未建立链接，不能开始");
                            continue;
                        }
                        Config.SENDER.start();
                        continue;
                    }
                    if (END.equalsIgnoreCase(words[0])) {
                        Config.SENDER.end();
                    }
                    if (EXIT.equalsIgnoreCase(words[0])) {
                        logger.info("程序结束！");
                        System.exit(0);
                    }
                    logger.info("没有任务!");
                }
            } catch (Exception e) {
                logger.error("处理任务出错", e);
            }

        }
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

}
