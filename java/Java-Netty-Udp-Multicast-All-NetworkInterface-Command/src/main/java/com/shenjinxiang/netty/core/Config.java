package com.shenjinxiang.netty.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    public static final String ENCODE = "UTF-8";
    public static String INDEX;
    public static int PORT = 8989;
    public static String HOST = "224.255.10.0";

    public static final List<NettyMulticastHandler> HANDLER_LIST = new ArrayList<>();
    public static final List<LocalAddress> LOCAL_ADDRESS_LIST = new ArrayList<>();

    static {
        initLocalAddressList();
    }

    private static void initLocalAddressList() {
        try {
            Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
            while (nifs.hasMoreElements()) {
                NetworkInterface ni = nifs.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    InetAddress addr = address.nextElement();
                    if (addr instanceof Inet4Address) {
                        LocalAddress localAddress = new LocalAddress();
                        localAddress.setNetworkInterface(ni);
                        localAddress.setInetAddress(addr);
                        LOCAL_ADDRESS_LIST.add(localAddress);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("出错了", e);
        }
    }
}
