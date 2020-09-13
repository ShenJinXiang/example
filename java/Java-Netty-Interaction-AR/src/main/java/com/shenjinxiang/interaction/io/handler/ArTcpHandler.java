package com.shenjinxiang.interaction.io.handler;

import com.shenjinxiang.interaction.core.Config;
import com.shenjinxiang.interaction.core.TcpDataUtil;
import com.shenjinxiang.interaction.entity.ControTcpCommand;
import com.shenjinxiang.interaction.entity.ControTcpResult;
import com.shenjinxiang.interaction.kit.ByteKit;
import com.shenjinxiang.interaction.kit.JsonKit;
import com.shenjinxiang.interaction.kit.StrKit;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ArTcpServerHandler
 * @Author ShenjinXiang
 * @Date 2020/9/11 23:02
 */
public class ArTcpHandler<T> extends TcpHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(ArTcpHandler.class);

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
        String content = msg.toString();
        if (StrKit.isBlank(content)) {
            return;
        }
        TcpDataUtil.handlerContent(content);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端断开连接：" + this.channel.remoteAddress() + " ID: " + this.channel.id());
        this.conn = false;
    }
}
