package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.io.handler.AlgTcpHandler;
import com.shenjinxiang.interaction.io.handler.ArTcpHandler;
import com.shenjinxiang.interaction.io.TcpHandler;
import com.shenjinxiang.interaction.io.handler.QtTcpHandler;

/**
 * @ClassName Const
 * @Author ShenjinXiang
 * @Date 2020/9/11 23:01
 */
public class Consts {

    public static final TcpHandler AR_SERVER_HANDLER = new ArTcpHandler();
    public static final int MAX_TCP_DATA_LENGTH = 1024 * 1024;


    /**
     * QT TCP 服务监听端口
     */
    public static final int QT_SERVER_LISTEN_PORT = 6001;

    /**
     * AR TCP 连接配置
     */
    public static final String AR_SERVER_HOST = "127.0.0.1";
    public static final int AR_SERVER_PORT = 6002;

    /**
     * Alg TCP 连接配置
     */
    public static final String ALG_SERVER_HOST = "127.0.0.1";
    public static final int ALG_SERVER_PORT = 6000;
    public static final int ALG_FIXED_DATA_LENGTH = 4096;



    public static final TcpHandler<String> QT_HANDLER = new QtTcpHandler();
    public static final TcpHandler<String> AR_HANDLER = new ArTcpHandler();
    public static final TcpHandler<byte[]> ALG_HANDLER = new AlgTcpHandler<>();
}
