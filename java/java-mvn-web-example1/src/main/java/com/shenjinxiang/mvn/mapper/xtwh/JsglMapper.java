package com.shenjinxiang.mvn.mapper.xtwh;

import com.shenjinxiang.mvn.rapid.domain.Bean;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/5 23:58
 */
public interface JsglMapper {
    
    String queryJsbh();

    int countByJsmc(Bean params);

    void insertJsxx(Bean params);

    void updateJsxx(Bean params);

    Bean queryJsxx(int jsid);

    void deleteJsxx(int jsid);

    void deleteJsqx(int jsid);

    int countRyJsByJsid(int jsid);

    void insertJsqx(Bean params);
}
