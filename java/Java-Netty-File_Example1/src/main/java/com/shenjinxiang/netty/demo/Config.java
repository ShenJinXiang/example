package com.shenjinxiang.netty.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Config {

    public static final BlockingQueue<String> LINES = new ArrayBlockingQueue<>(5);
}
