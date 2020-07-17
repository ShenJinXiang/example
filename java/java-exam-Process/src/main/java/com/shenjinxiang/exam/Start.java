package com.shenjinxiang.exam;

import com.shenjinxiang.exam.utils.ThreadPool;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/17 8:42
 */
public class Start {

    public static void main(String[] args) {
        String command = "E:\\temp\\20200716\\aaa.bat";
//        ShellRun shell = new ShellRun(command);
//        String command = "echo %PATH%";
        ThreadPool.getThread().execute(new ShellRun(command));

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                shell.run();
//            }
//        }, 100, 100);
    }
}
