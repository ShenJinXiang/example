package com.shenjinxiang.exam;

import com.shenjinxiang.exam.jsoup.JsoupTest;
import com.shenjinxiang.exam.jsoup.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/2 10:59
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) throws Exception {
        logger.info("start...");
//        new FileOutTest().run();
//        new FileInTest().run();
//        ThreadPool.getThread().execute(new Producer(50));
//        ThreadPool.getThread().execute(new Consumer(100));
//        JsoupTest.get();
//        JsoupTest.delete(11);
        Student student = new Student();
        student.setName("张三1");
        student.setAge(23);
        student.setDesc("sfdsf阿迪爽肤水");
        student.setSex(0);
        JsoupTest.add(student);
        logger.info("end...");
    }
}
