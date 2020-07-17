package com.shenjinxiang.spb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/17 9:54
 */
@Component
@ConfigurationProperties(prefix = "config")
public class MyConfig {

    private String pythonCommand;

    public String getPythonCommand() {
        return pythonCommand;
    }

    public void setPythonCommand(String pythonCommand) {
        this.pythonCommand = pythonCommand;
    }
}
