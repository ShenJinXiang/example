package com.shenjinxiang.client.kit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.shenjinxiang.client.core.Config;

import java.util.concurrent.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/6/2 18:10
 */
public class ThreadPool {

    private static ExecutorService threadPool;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("Client-Thread-%d").build();
        int size = Config.THREAD_POOL_SIZE;
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
