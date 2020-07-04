package com.shenjinxiang.gtest.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/4 10:50
 */
public class StrKitTest {

    @Test
    public void test1() {
        Assert.assertFalse(StrKit.isBlank("aaa"));
    }
}
