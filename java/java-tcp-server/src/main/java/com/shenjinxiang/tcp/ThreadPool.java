package com.shenjinxiang.tcp;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/6/2 18:10
 */
public class ThreadPool {

    private static ExecutorService threadPool;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("Transform-Thread-%d").build();
        int size = 10;
        threadPool = Executors.newFixedThreadPool(size, namedThreadFactory);
    }

    private ThreadPool() {

    }

    public static ExecutorService getThread() {
        return threadPool;
    }
}
