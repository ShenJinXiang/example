package com.shenjinxiang.mvn.actions.common;

import com.shenjinxiang.mvn.rapid.actions.BaseCommonAction;
import com.shenjinxiang.mvn.rapid.domain.Bean;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 22:04
 */
public class CommonAction extends BaseCommonAction {


    public void index() {
        System.out.println("111");
        Bean bean = new Bean();
        bean.set("name", "aaa");
//        setJson(bean);
    }
}
