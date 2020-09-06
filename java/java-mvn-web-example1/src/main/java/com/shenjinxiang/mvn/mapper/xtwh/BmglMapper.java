package com.shenjinxiang.mvn.mapper.xtwh;

import com.shenjinxiang.mvn.rapid.domain.Bean;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/3 06:52
 */
public interface BmglMapper {

    String queryBmbh();

    String queryBmid(String sjbmid);

    void insertBmxx(Bean bm);

    Bean queryBmxx(String bmid);

    void updateBmxx(Bean params);

    int countBySjbmid(String sjbmid);

    void deleteByBmid(String bmid);

    List<Bean> queryBmxxForTree();
}
