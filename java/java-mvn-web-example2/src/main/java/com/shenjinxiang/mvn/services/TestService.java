package com.shenjinxiang.mvn.services;

import com.shenjinxiang.mvn.mapper.TestMapper;
import com.shenjinxiang.rapid.annotations.MyBatisDbConn;
import com.shenjinxiang.rapid.domain.Bean;
import com.shenjinxiang.rapid.plugin.mybatis.MyBatisSessionManager;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/3 21:40
 */
public class TestService {

    @MyBatisDbConn
    public List<Bean> queryAllZyList() {
        TestMapper testMapper = MyBatisSessionManager.getMapper(TestMapper.class);
        List<Bean> list = testMapper.queryAllZyList();
        return list;
    }
}
