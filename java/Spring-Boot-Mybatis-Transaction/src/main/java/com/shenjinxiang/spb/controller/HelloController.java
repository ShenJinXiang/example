package com.shenjinxiang.spb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 17:03
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("")
    public String index() {
        return "Spring-Boot-Mybatis-Transaction!";
    }
}
