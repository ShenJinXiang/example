package com.shenjinxiang.gtest;

import com.shenjinxiang.gtest.utils.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/4 10:46
 */
public class Start {

//    private static final Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {
//        logger.info("start");
        System.out.println(StrKit.isBlank(""));
        System.out.println(StrKit.isBlank("aaa"));
        System.out.println(StrKit.isBlank("aaa "));
        System.out.println(StrKit.isBlank(" aaa "));
//        logger.info("end");
    }
}
