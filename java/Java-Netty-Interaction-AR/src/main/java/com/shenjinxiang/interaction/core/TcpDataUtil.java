package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.entity.ControTcpCommand;
import com.shenjinxiang.interaction.entity.ControTcpResult;
import com.shenjinxiang.interaction.entity.RunStatus;
import com.shenjinxiang.interaction.entity.Wave;
import com.shenjinxiang.interaction.io.IOKit;
import com.shenjinxiang.interaction.kit.ByteArrayConveter;
import com.shenjinxiang.interaction.kit.ByteKit;
import com.shenjinxiang.interaction.kit.JsonKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
        List<Wave> waves = parase(data);
        logger.info("接收到内容：" + JsonKit.toJson(waves));
    }

    private static List<Wave> parase(String str) {
        int len = str.length() / 4;
        String[] strs = new String[] {
                str.substring(0, len),
                str.substring(len, 2 * len),
                str.substring(2 * len, 3 * len),
                str.substring(3 * len, 4 * len)
        };
        List<Wave> waves = new ArrayList<>();

        for(String s: strs) {
            byte[] bytes = ByteKit.hexStrToByteArray(s);
            Wave wave = new Wave();
            int p = ByteArrayConveter.getInt(bytes, 0);
            wave.setPickpoint(p);
            int index = 4;
            List<Float> floats = new ArrayList<>();
            while (index < bytes.length) {
                float f = ByteArrayConveter.getFloat(bytes, index);
                floats.add(f);
                index+=4;
            }
            wave.setData(floats);
            waves.add(wave);
        }
        return waves;
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
