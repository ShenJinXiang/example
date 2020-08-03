package com.shenjinxiang.transform.core;

import com.shenjinxiang.transform.config.TransformConfig;
import com.shenjinxiang.transform.domain.ConnType;
import com.shenjinxiang.transform.domain.TransformGroup;
import com.shenjinxiang.transform.domain.TransformReceive;
import com.shenjinxiang.transform.domain.TransformSend;
import com.shenjinxiang.transform.io.NettyTcpClient;
import com.shenjinxiang.transform.io.NettyTcpServer;
import com.shenjinxiang.transform.io.NettyUdpServer;
import com.shenjinxiang.transform.kit.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.shenjinxiang.transform.domain.ConnType.TCP;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 15:31
 */
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    public static void init() throws Exception {
        List<TransformGroup> groupList = TransformConfig.groupList;
        if (null == groupList || groupList.size() <= 0) {
            throw new Exception("未加载到数据传输配置内容，检查transform.json文件！");
        }
        for(TransformGroup group : groupList) {
            if (group.isEnable()) {
                TransformReceive receive = group.getReceive();
                switch (receive.getType()) {
                    case UDP:
                        ThreadPool.getThread().execute(new NettyUdpServer(group));
                        break;
                    case TCP:
                    default:
                        ThreadPool.getThread().execute(new NettyTcpServer(group));
                        break;
                }
                List<TransformSend> sendList = group.getSendList();
                for (TransformSend send: sendList) {
                    ThreadPool.getThread().execute(new NettyTcpClient(send));
                }
            }
        }
    }
}
