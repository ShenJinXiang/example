package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.entity.ControTcpCommand;
import com.shenjinxiang.interaction.entity.ControTcpResult;
import com.shenjinxiang.interaction.entity.RunStatus;
import com.shenjinxiang.interaction.io.IOKit;
import com.shenjinxiang.interaction.kit.ByteKit;
import com.shenjinxiang.interaction.kit.JsonKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpDataUtil {

    private static final Logger logger = LoggerFactory.getLogger(TcpDataUtil.class);

    public static void handlerContent(String content) {
        if (content.startsWith(Config.WAVE_DATA_PREFIX)) {
            String data = content.substring(Config.WAVE_DATA_PREFIX.length());
            TcpDataUtil.handlerWaveData(data);
        } else {
            handlerCommand(content);
        }
    }

    public static void handlerWaveData(String data) {
        Config.RUNSTATUS = RunStatus.WORK;
        byte[] bytes = ByteKit.hexStrToByteArray(data);
        logger.info("接收到波形数据，数据长度: " + bytes.length);
        logger.info("内容: " + ByteKit.byteArrayToHexStr(bytes));
    }

    private static void handlerCommand(String content) {
        ControTcpCommand controTcpCommand = JsonKit.fromJson(content, ControTcpCommand.class);
        String command = controTcpCommand.getCommand();
        String token = controTcpCommand.getToken();
        if ("isReady".equalsIgnoreCase(command)) {
            handlerIsReady(controTcpCommand);
            return;
        }
        if ("start".equalsIgnoreCase(command)) {
            handlerStart(controTcpCommand);
            return;
        }
        if ("end".equalsIgnoreCase(command)) {
            handlerEnd(controTcpCommand);
            return;
        }
        sendControTcpResult(ControTcpResult.error(token, "无此命令"));
    }

    private static void sendControTcpResult(ControTcpResult result) {
        IOKit.sendTcpMsg(JsonKit.toJson(result));
    }

    private static void handlerEnd(ControTcpCommand controTcpCommand) {
        Config.RUNSTATUS = RunStatus.NONE;
        Config.SENDER.end();
    }

    private static void handlerStart(ControTcpCommand controTcpCommand) {
        Config.SENDER.begin();
    }

    private static void handlerIsReady(ControTcpCommand controTcpCommand) {
        String token = controTcpCommand.getToken();
        switch (Config.RUNSTATUS) {
            case WORK:
                sendControTcpResult(ControTcpResult.error(token, "正在考核中，不能再次开始"));
                return;
            case READY_FOR_WORK:
                sendControTcpResult(ControTcpResult.error(token, "正在准备开始考核，不能再次开始"));
                return;
            case PLAY_RECORD:
                sendControTcpResult(ControTcpResult.error(token, "正在回放历史记录，不能开始考核"));
                return;
            default:
            case NONE:
                Config.RUNSTATUS = RunStatus.READY_FOR_WORK;
                sendControTcpResult(ControTcpResult.success(token));
        }
    }

}
