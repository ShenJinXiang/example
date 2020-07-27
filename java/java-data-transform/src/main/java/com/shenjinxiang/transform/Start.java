package com.shenjinxiang.transform;

import com.shenjinxiang.transform.kit.PathKit;
import com.shenjinxiang.transform.kit.ThreadPool;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 15:44
 */
public class Start {

    public static void main(String[] args) throws Exception {
        System.out.println(PathKit.isJar());
        System.out.println(PathKit.getCrrentFilePath());
        System.out.println(PathKit.getCurrentPath());
        ThreadPool.getThread();
    }
}
