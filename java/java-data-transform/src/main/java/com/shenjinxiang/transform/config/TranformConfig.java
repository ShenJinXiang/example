package com.shenjinxiang.transform.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 16:24
 */
public class TranformConfig {

    private static final List<Map<String, Object>> list = new ArrayList<>();

    static {
        loadTransform();
    }

    private static void loadTransform() {

    }

}
