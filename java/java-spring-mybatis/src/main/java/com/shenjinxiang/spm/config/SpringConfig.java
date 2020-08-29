package com.shenjinxiang.spm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/26 21:34
 */
@Configuration
@ComponentScan(
        basePackages = "com.shenjinxiang.spm",
        useDefaultFilters = true,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)}
)
public class SpringConfig {
}
