package com.shenjinxiang.transform.handler;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 17:03
 */
public class TcpDataHandler implements DataHandler {
    @Override
    public String handle(byte[] data) {
        return new String(data) + "\n";
    }
}
