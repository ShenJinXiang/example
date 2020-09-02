package com.shenjinxiang.mvn.mapper.xtwh;

import com.shenjinxiang.mvn.rapid.domain.Bean;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 21:53
 */
public interface ZyglMapper {

    List<Bean> queryAllZyList();

    String queryZyid(String sjzyid);

    void saveZyxx(Bean zyxx);

    Bean selectZyxx(String zyid);

    int countByZySjjyid(String sjzyid);

    void deleteByZyid(String zyid);

    void updateByZyid(Bean zyxx);
}
