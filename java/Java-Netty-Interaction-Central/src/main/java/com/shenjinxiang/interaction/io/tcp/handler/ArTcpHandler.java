package com.shenjinxiang.interaction.io.tcp.handler;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.io.IOKit;
import com.shenjinxiang.interaction.kit.ByteKit;
import com.shenjinxiang.interaction.kit.JsonKit;
import com.shenjinxiang.interaction.kit.StrKit;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

/**
 * @ClassName ArTcpServerHandler
 * @Author ShenjinXiang
 * @Date 2020/9/11 23:02
 */
public class ArTcpHandler<T> extends TcpHandler<T> {

    private static final String WAVE_DATA_PREFIX = Config.WAVE_DATA_PREFIX;

    /**
     * 发送消息
     * @param msg
     */
    @Override
    public void sendMsg(T msg) {
        if (conn) {
            this.channel.writeAndFlush(msg + "\n");
        }
    }

    /**
     * 接收消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        channel = ctx.channel();
        String content = msg.toString();
        if (StrKit.isBlank(content)) {
            return;
        }
        if (content.startsWith(WAVE_DATA_PREFIX)) {
            String data = content.substring(WAVE_DATA_PREFIX.length());
            IOKit.sendAlgTcpMsg(ByteKit.hexStrToByteArray(data));
        } else {
            Map<String, Object> map = JsonKit.fromJson(content, Map.class);
            String token = (String) map.get("token");
            Config.RESULT_MAP.put(token, map);
        }
    }
}
