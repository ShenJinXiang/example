package com.shenjinxiang.rxtx.io;

import com.shenjinxiang.rxtx.kit.ThreadPool;

public class IOKit {

    public static final RxtxHandler RXTX_HANDLER = new RxtxHandler();

    public static void runRxtx(String serialNumber) {
        if (!RXTX_HANDLER.isConn()) {
            ThreadPool.getThread().execute(new RxtxClient(serialNumber, RXTX_HANDLER));
        }
    }

    public static void close() {
        RXTX_HANDLER.close();
    }

    public static void sendData(byte[] bytes) {
        RXTX_HANDLER.sendData(bytes);
    }

}
