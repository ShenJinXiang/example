package com.shenjinxiang.exam.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/2 18:50
 */
public class Data {

    private static Queue<String> queue = new LinkedList<>();


    public static boolean offer(String str) {
        return queue.offer(str);
    }

    public static String poll() {
        return queue.poll();
    }

    public static int size() {
        return queue.size();
    }
}
