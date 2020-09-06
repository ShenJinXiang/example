package com.shenjinxiang.mvn.mapper.xtwh;

import com.shenjinxiang.mvn.rapid.domain.Bean;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 23:02
 */
public interface RyglMapper {

    int countBySsbmid(String ssbmid);

    String queryRybh();

    Bean queryRyxx(int ryid);

    int countByRyzh(Bean ryxx);

    void saveRyxx(Bean ryxx);

    void updateRyxx(Bean ryxx);

    void deleteRyJsxx(int ryid);

    void deleteRyxx(int ryid);

    void resetPwd(Bean ryxx);

    void insertRyjs(Bean params);

    List<Bean> queryJsForTree(int ryid);
}
