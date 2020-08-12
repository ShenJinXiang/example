package com.shenjinxiang.data.core;

import com.shenjinxiang.data.kit.RandomKit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/10 21:04
 */
public class WaveData {


    private Element element;
    private List<Double> list;
    private int peak;

    public WaveData(int index) {
        this.element = new Element(index);
    }

    public Map<String, Object> createDate() {
        list = new ArrayList<>();
        for(int i = 0; i < Consts.LENGTH; i++) {
            list.add(RandomKit.randomDouble(Consts.MIN_VAL, Consts.MAX_VAL));
        }
        element.run(1);
        element.replace(list);
        if (element.getIndex() > list.size() - 3) {
            element.reset();
        }
        peak = element.getIndex() + 2;
        Map<String, Object> map = new HashMap<>();
        map.put("wave", list);
        map.put("peak_point", peak);

        return map;
    }
}
