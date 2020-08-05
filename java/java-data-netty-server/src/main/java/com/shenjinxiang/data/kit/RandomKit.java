package com.shenjinxiang.data.kit;

import java.util.Random;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/5 17:53
 */
public class RandomKit {

    private static final Random random = new Random();

    public static double randomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public static double randomInt(int min, int max) {
        return min + random.nextInt(max - min);
    }
}
