package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.io.IOKit;
import com.shenjinxiang.interaction.kit.JsonKit;
import com.shenjinxiang.interaction.kit.StrKit;
import com.shenjinxiang.interaction.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 22:00
 */
public class CommandReader implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private static final String PREPARE = "prepare";
    private static final String START = "start";
    private static final String END = "end";
    private static final String EXIT = "exit";
    private static final String CLOSE = "close";
    private static final String CONN = "conn";

    private BufferedReader bufferedReader;

    public CommandReader() throws UnsupportedEncodingException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
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
                        IOKit.runMulticastUdpServer();
                        IOKit.runPointUdpServer();
                        IOKit.runDdsjFileReader();
                        Thread.sleep(3000);
                        continue;
                    }
                    if (CLOSE.equalsIgnoreCase(words[0])) {
                        IOKit.closePointUdpServer();
                        IOKit.closeMulticastUdpServer();
                        continue;
                    }
                    // 准备
                    if (PREPARE.equalsIgnoreCase(words[0])) {
                        IOKit.runAlgClient();
                        IOKit.runArClient();
                        continue;
                    }
                    // 开始
                    if (START.equalsIgnoreCase(words[0])) {
                        if (!IOKit.isAlgConn()) {
                            logger.info("和算法中心未建立连接，不能开始考核");
                            continue;
                        }
                        if (!IOKit.isArConn()) {
                            logger.info("和AR模拟仿真未建立连接，不能开始考核");
                            continue;
                        }
                        Map<String, Object> data = new HashMap<>();
                        data.put("command", "isReady");
                        Map<String, Object> result = IOKit.sendArMessageSync(data);
                        if (!(boolean) result.get("success")) {
                            logger.info("AR模拟仿真尚未准备好，不能开始考核");
                            continue;
                        }
                        data = new HashMap<>();
                        data.put("ksid", "1");
                        data.put("command", "start");
                        IOKit.sendArTcpMsg(JsonKit.toJson(data));
                    }
                    // 结束
                    if (END.equalsIgnoreCase(words[0])) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("ksid", "1");
                        data.put("command", "end");
                        IOKit.sendArTcpMsg(JsonKit.toJson(data));
                        IOKit.closeAlgClient();
                        IOKit.closeArClient();
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
