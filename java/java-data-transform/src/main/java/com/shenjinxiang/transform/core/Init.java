package com.shenjinxiang.transform.core;

import com.shenjinxiang.transform.config.ConfigProperties;
import com.shenjinxiang.transform.config.TransformConfig;
import com.shenjinxiang.transform.kit.PathKit;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 16:45
 */
public class Init {

    private static final Logger logger = LoggerFactory.getLogger(Init.class);

    private static final Init init = new Init();

    private Init() {}

    private void initJar() {
        Consts.setJar(PathKit.isJar());
    }

    private void initCurrentPath() throws Exception {
        String path = PathKit.getCurrentPath();
        logger.info("当前运行文件目录：" + path);
        Consts.setCurrentPath(path);
    }

    private void initCommandLine(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("dev", "是否开发者模式运行");
        // 解析命令行参数
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);
        boolean devMode = cl.hasOption("dev");
        Consts.setDevMode(devMode);
    }

    public static void run(String[] args) throws Exception {
        init.initCommandLine(args);
        logger.info("开发模式：" + Consts.isDevMode());
        init.initJar();
        if (Consts.isJar()) {
            init.initCurrentPath();
        }
        TransformConfig.init();
        NettyServer.init();
    }
}
