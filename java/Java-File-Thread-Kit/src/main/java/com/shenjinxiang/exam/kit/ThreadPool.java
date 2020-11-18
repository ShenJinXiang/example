package com.shenjinxiang.exam.kit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/6/2 18:10
 */
public class ThreadPool {

    private static ExecutorService threadPool;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("Transform-Thread-%d").build();
        int size = 20;
//        threadPool = Executors.newFixedThreadPool(size, namedThreadFactory);
        threadPool = new ThreadPoolExecutor(size, size,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                namedThreadFactory);
    }

    private ThreadPool() {

    }

    public static ExecutorService getThread() {
        return threadPool;
    }
}
