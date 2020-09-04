package com.shenjinxiang.appjk.controller;

import com.shenjinxiang.appjk.entity.Result;
import com.shenjinxiang.appjk.services.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexService indexService;

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @PostMapping("/tj")
    @ResponseBody
    public Result tjData(String url, String params, int check) {
        return indexService.tjData(url, params, check);
    }

}
