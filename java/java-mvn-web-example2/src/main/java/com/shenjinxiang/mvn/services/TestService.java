package com.shenjinxiang.mvn.services;

import com.shenjinxiang.mvn.mapper.TestMapper;
import com.shenjinxiang.rapid.annotations.MyBatisDbConn;
import com.shenjinxiang.rapid.annotations.MyBatisDbConnTx;
import com.shenjinxiang.rapid.domain.Bean;
import com.shenjinxiang.rapid.exceptions.BizException;
import com.shenjinxiang.rapid.plugin.mybatis.MyBatisSessionManager;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/3 21:40
 */
public class TestService {

    @MyBatisDbConnTx
    public List<Bean> queryAllZyList() {
        TestMapper testMapper = MyBatisSessionManager.getMapper(TestMapper.class);
        if (true) {
            throw new BizException("hahaha");
        }
        List<Bean> list = testMapper.queryAllZyList();
        return list;
    }
}
