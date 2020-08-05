package com.shenjinxiang.data.core;

import com.shenjinxiang.data.kit.RandomKit;

import java.util.List;
import java.util.Random;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/5 17:44
 */
public class Element {

    private double[] data;
    private int index = 0;

    public Element() {
        data = new double[5];
        data[0] = RandomKit.randomDouble(Consts.MIN_PEAK_VAL * 0.3, Consts.MAX_PEAK_VAL * 0.3);
        data[1] = RandomKit.randomDouble(Consts.MIN_PEAK_VAL * 0.6, Consts.MAX_PEAK_VAL * 0.6);
        data[2] = RandomKit.randomDouble(Consts.MIN_PEAK_VAL * 1, Consts.MAX_PEAK_VAL * 1);
        data[3] = RandomKit.randomDouble(Consts.MIN_PEAK_VAL * 0.5, Consts.MAX_PEAK_VAL * 0.5);
        data[4] = RandomKit.randomDouble(Consts.MIN_PEAK_VAL * 0.3, Consts.MAX_PEAK_VAL * 0.3);
    }

    public void run(int step) {
        this.index += step;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void replace(List<Double> list) {
        if (this.index < list.size() - 6) {
            for (int i = 0; i < 5; i++) {
                list.set(i + this.index, data[i]);
            }
        }
    }
}
