package com.shenjinxiang.rxtx.rxtx;

import com.shenjinxiang.rxtx.core.Consts;
import com.shenjinxiang.rxtx.kit.ByteKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.shenjinxiang.rxtx.core.Consts.SERIAL_PORT_SERVER;

public class RxtxKit {

    private static final Logger logger = LoggerFactory.getLogger(RxtxKit.class);

    private static boolean conn = false;

    public static void conn() {
        Consts.RXTX_CONFIG = new RxtxConfig(
                Consts.RXTX_SERIAL_NUMBER,
                Consts.RXTX_BAUDRATE,
                Consts.RXTX_CHECKOUT_BIT,
                Consts.RXTX_DATA_BIT,
                Consts.RXTX_STOP_BIT
        );
        SERIAL_PORT_SERVER = new SerialPortServer(Consts.RXTX_CONFIG);
        conn = SERIAL_PORT_SERVER.start();
        if (conn) {
            logger.info("已链接串口[" + Consts.RXTX_SERIAL_NUMBER + "]");
            logInfo();
        }
    }

    public static void close() {
        if (conn) {
            SERIAL_PORT_SERVER.stop();
            conn = false;
            logger.info("已关闭串口链接！");
        } else {
            logger.info("未连接串口，无法停止！");
        }
    }

    public static boolean isConn() {
        return conn;
    }

    public static void sendData(byte[] bytes) {
        if (conn) {
            SERIAL_PORT_SERVER.send(bytes);
        }
    }

    public static void sendData(String data) {
        if (conn) {
            byte[] bytes = ByteKit.hexStrToByteArray(data);
            SERIAL_PORT_SERVER.send(bytes);
        }
    }

    private static void logInfo() {
        StringBuilder stringBuilder = new StringBuilder("当前串口配置信息：");
        stringBuilder.append("\n\t串口号：").append(Consts.RXTX_SERIAL_NUMBER)
                .append("\n\t波特率：").append(Consts.RXTX_BAUDRATE)
                .append("\n\t校验位：").append(Consts.RXTX_CHECKOUT_BIT)
                .append("\n\t数据位：").append(Consts.RXTX_DATA_BIT)
                .append("\n\t停止位：").append(Consts.RXTX_STOP_BIT);
        logger.info(stringBuilder.toString());
    }
}
