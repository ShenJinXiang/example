package com.shenjinxiang.interaction.io;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.io.file.FileLineReader;
import com.shenjinxiang.interaction.io.tcp.handler.AlgTcpHandler;
import com.shenjinxiang.interaction.io.tcp.handler.ArTcpHandler;
import com.shenjinxiang.interaction.io.tcp.handler.QtTcpHandler;
import com.shenjinxiang.interaction.io.tcp.handler.TcpHandler;
import com.shenjinxiang.interaction.io.tcp.TcpClient;
import com.shenjinxiang.interaction.io.tcp.TcpServer;
import com.shenjinxiang.interaction.io.udp.UdpMulticastServer;
import com.shenjinxiang.interaction.io.udp.UdpServer;
import com.shenjinxiang.interaction.io.udp.handler.PointUdpHandler;
import com.shenjinxiang.interaction.io.udp.handler.UdpHandler;
import com.shenjinxiang.interaction.io.udp.handler.UdpMulticastHandler;
import com.shenjinxiang.interaction.kit.JsonKit;
import com.shenjinxiang.interaction.kit.ThreadPool;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/12 06:54
 */
public class IOKit {

    private static final Logger logger = LoggerFactory.getLogger(IOKit.class);

    private static final int MAX_TCP_DATA_LENGTH;

    private static final int QT_SERVER_LISTEN_PORT;

    private static final String AR_SERVER_HOST;
    private static final int AR_SERVER_PORT;

    private static final String ALG_SERVER_HOST;
    private static final int ALG_SERVER_PORT;
    private static final int ALG_FIXED_DATA_LENGTH;

    private static final int UDP_SERVER_LISTEN_PORT;

    private static final String UDP_MULTICAST_NETWOKINTERFACE;
    private static final String UDP_MULTICAST_HOST;
    private static final int UDP_MULTICAST_PORT;


    private static final TcpHandler<String> QT_HANDLER;
    private static final TcpHandler<String> AR_HANDLER;
    private static final TcpHandler<byte[]> ALG_HANDLER;

    private static final InetSocketAddress MULTICAST_GROUP_ADDRESS;
    private static final UdpHandler POINT_HANDLER;
    private static final UdpHandler MULTICAST_HANDLER;

    private static final String DDSJ_FILE_PATH;
    private static final FileLineReader DDSJ_READER;

    private static boolean QT_START = false;

    static {
        MAX_TCP_DATA_LENGTH = Config.CENTRAL_CONFIG.getMaxTcpDataLength();
        QT_SERVER_LISTEN_PORT = Config.CENTRAL_CONFIG.getQtServerListenPort();

        AR_SERVER_HOST = Config.CENTRAL_CONFIG.getArServerHost();
        AR_SERVER_PORT = Config.CENTRAL_CONFIG.getArServerPort();

        ALG_SERVER_HOST = Config.CENTRAL_CONFIG.getAlgServerHost();
        ALG_SERVER_PORT = Config.CENTRAL_CONFIG.getAlgServerPort();
        ALG_FIXED_DATA_LENGTH = Config.CENTRAL_CONFIG.getAlgFixedDataLength();

        UDP_SERVER_LISTEN_PORT = Config.CENTRAL_CONFIG.getUdpServerListenPort();

        UDP_MULTICAST_NETWOKINTERFACE = Config.CENTRAL_CONFIG.getUdpMulticastNetwokinterface();
        UDP_MULTICAST_HOST = Config.CENTRAL_CONFIG.getUdpMulticastHost();
        UDP_MULTICAST_PORT = Config.CENTRAL_CONFIG.getUdpMulticastPort();

        DDSJ_FILE_PATH = Config.CENTRAL_CONFIG.getDdFilePath();

        QT_HANDLER = new QtTcpHandler<String>();
        AR_HANDLER = new ArTcpHandler<String>();
        ALG_HANDLER = new AlgTcpHandler<byte[]>();

        MULTICAST_GROUP_ADDRESS = new InetSocketAddress(UDP_MULTICAST_HOST, UDP_MULTICAST_PORT);
        POINT_HANDLER = new PointUdpHandler(UDP_SERVER_LISTEN_PORT);
        MULTICAST_HANDLER = new UdpMulticastHandler(MULTICAST_GROUP_ADDRESS);

        DDSJ_READER = new FileLineReader(new File(DDSJ_FILE_PATH));
    }

    /**
     * 启动和qt对接的tcp服务端
     */
    public static void runQtServer() {
        if (!QT_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new TcpServer(
                    QT_SERVER_LISTEN_PORT,
                    createLineBaseChannelInitializer(QT_HANDLER)
            ));

        }
    }

    /**
     * 启动AR对接的tcp客户端
     */
    public static void runArClient() {
        if (!AR_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new TcpClient(
                    AR_SERVER_HOST,
                    AR_SERVER_PORT,
                    createLineBaseChannelInitializer(AR_HANDLER)
            ));
        }
    }


    /**
     * 启动算法中心对接的TCP客户端
     */
    public static void runAlgClient() {
        logger.info("开始建立和算法中心连接，地址[" + ALG_SERVER_HOST + ":" + ALG_SERVER_PORT + "]");
        if (ALG_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new TcpClient(
                    ALG_SERVER_HOST,
                    ALG_SERVER_PORT,
                    new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new FixedLengthFrameDecoder(ALG_FIXED_DATA_LENGTH));
                            channel.pipeline().addLast(ALG_HANDLER);
                        }
                    }
            ));
        }
    }

    /**
     * 启动UPD服务 发送udp数据
     */
    public static void runPointUdpServer() {
        if (!POINT_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new UdpServer(
                    UDP_SERVER_LISTEN_PORT,
                    POINT_HANDLER
            ));
        }
    }

    /**
     * 启动UDP 组播
     */
    public static void runMulticastUdpServer() {
        if (!MULTICAST_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new UdpMulticastServer(
                    MULTICAST_GROUP_ADDRESS,
                    UDP_MULTICAST_NETWOKINTERFACE,
                    MULTICAST_HANDLER
            ));
        }
    }

    public static void runDdsjFileReader() {
        ThreadPool.getThread().execute(DDSJ_READER);
    }

    private static ChannelInitializer createLineBaseChannelInitializer(TcpHandler tcpHandler) {
        return new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new LineBasedFrameDecoder(MAX_TCP_DATA_LENGTH));
                channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                channel.pipeline().addLast(tcpHandler);
                channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
            }
        };
    }


    /**
     * @Description: 关闭Qt TCP服务
     * @Author: ShenJinXiang
     * @return: void
     **/
    public static void closeQtServer() {
        if (QT_HANDLER.isConn()) {
            QT_HANDLER.close();
        }
    }

    /**
     * @Description: 关闭Ar TCP链接
     * @Author: ShenJinXiang
     * @return: void
     **/
    public static void closeArClient() {
        if (AR_HANDLER.isConn()) {
            AR_HANDLER.close();
        }
    }

    /**
     * @Description: 关闭和算法中心的TCP连接
     * @Author: ShenJinXiang
     * @return: void
     **/
    public static void closeAlgClient() {
        if (ALG_HANDLER.isConn()) {
            ALG_HANDLER.close();
        }
    }

    /**
     * @Description: 关闭UDP 监听服务
     * @Author: ShenJinXiang
     * @return: void
     **/
    public static void closePointUdpServer() {
        if (POINT_HANDLER.isConn()) {
            POINT_HANDLER.close();
        }
    }

    /**
     * @Description: 推出UDP 组播
     * @Author: ShenJinXiang
     * @return: void
     **/
    public static void closeMulticastUdpServer() {
        if (MULTICAST_HANDLER.isConn()) {
            MULTICAST_HANDLER.close();
        }
    }


    /**
     * @Description: 关闭读取dd数据线程
     * @Author: ShenJinXiang
     * @return: void
     **/
    public static void closeDdsjReader() {
        DDSJ_READER.close();
    }

    public static boolean isQtConn() {
        return QT_HANDLER.isConn();
    }

    public static boolean isQtStart() {
        return QT_START;
    }

    public static void setQtStart(boolean qtStart) {
        QT_START = qtStart;
    }

    public static boolean isArConn() {
        return AR_HANDLER.isConn();
    }

    public static boolean isAlgConn() {
        return ALG_HANDLER.isConn();
    }

    public static boolean isPointUdpConn() {
        return POINT_HANDLER.isConn();
    }

    public static boolean isMulticastUdpConn() {
        return MULTICAST_HANDLER.isConn();
    }

    /**
     * @Description: 读取弹道数据
     * @Author: ShenJinXiang
     * @return: java.lang.String
     **/
    public static String readDdsj() {
        return DDSJ_READER.readLine();
    }

    public static void sendPointUdpMsg(byte[] data, InetSocketAddress address) {
        POINT_HANDLER.sendMsg(data, address);
    }

    public static void sendAlgTcpMsg(byte[] data) {
        ALG_HANDLER.sendMsg(data);
    }

    public static void sendQtTcpMsg(String data) {
        QT_HANDLER.sendMsg(data);
    }


    public static void sendArTcpMsg(String data) {
        AR_HANDLER.sendMsg(data);
    }

    /**
     * 发送消息，同步模式，等待获取结果
     * @param data
     * @return
     */
    public static Map<String, Object> sendArMessageSync(Map<String, Object> data) {
        String token = UUID.randomUUID().toString().replace("-", "");
        data.put("token", token);
        AR_HANDLER.sendMsg(JsonKit.toJson(data));
        if (null != token) {
            return resultByToken(token);
        }
        return null;
    }

    private static Map<String, Object> resultByToken(String token) {
        while (true) {
            try {
                if (Config.RESULT_MAP.containsKey(token)) {
                    Map<String, Object> data = (Map<String, Object>) Config.RESULT_MAP.get(token);
                    Config.RESULT_MAP.remove(token);
                    if (null != data && data.containsKey(token)) {
                        data.remove(token);
                    }
                    return data;
                }
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
