package com.shenjinxiang.rxtx.kit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 22:50
 */
public class ThreadPool {

    private static ExecutorService threadPool;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("Rxtx-Thread-%d").build();
        int size = 20;
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
