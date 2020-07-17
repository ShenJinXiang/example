package com.shenjinxiang.exam.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadPool {

    private static ExecutorService fixedThreadPool;

    private ThreadPool() {}

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("exam-Thread-%d").build();
        fixedThreadPool = Executors.newCachedThreadPool(namedThreadFactory);
    }

    public static ExecutorService getThread() {
        return fixedThreadPool;
    }

    public void down() {
        fixedThreadPool.shutdown();
    }
}
