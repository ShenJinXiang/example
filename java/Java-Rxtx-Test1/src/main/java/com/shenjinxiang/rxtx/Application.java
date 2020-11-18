package com.shenjinxiang.rxtx;

import com.shenjinxiang.rxtx.kit.ThreadPool;
import com.shenjinxiang.rxtx.rxtx.RxtxKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        RxtxKit.conn();
    }
}
