package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.entity.config.CentralConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Const
 * @Author ShenjinXiang
 * @Date 2020/9/11 23:01
 */
public class Config {

    public static CentralConfig CENTRAL_CONFIG;

    public static final String WAVE_DATA_PREFIX = "DATA";

    public static final Sender SENDER = new Sender();

    public static final Map<String, Object> RESULT_MAP = new HashMap<>();

}
