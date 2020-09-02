package com.shenjinxiang.mvn.rapid.actions;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Page;
import com.shenjinxiang.mvn.rapid.exceptions.RapidException;
import com.shenjinxiang.mvn.rapid.interceptors.JsonResultInterceptor;
import com.shenjinxiang.mvn.rapid.services.CommonService;

import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 21:46
 */
@Before(JsonResultInterceptor.class)
public class BaseCommonAction extends RapidAction {

    @Inject
    private CommonService commonService;

    /**
     * 注入参数处理
     * @param parameters
     */
    public void handlerPar(Map<String, Object> parameters) throws Exception {

    }

    /**
     * 分页查询
     * @throws Exception
     */
    public void extQuery() throws Exception{
        Map<String, Object> parameters = (Map<String, Object>) this.getParams();
        handlerPar(parameters);
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");
        String sqlid = getPara("sqlid");
        if (sqlid==null) {
            throw new RapidException("未设置sqlid！");
        }
//        Page<Map<String, Object>> result = CommonDao.dao.paginateByXml(page,limit,"Dynasearch_"+sqlid+"_count", "Dynasearch_"+sqlid+"_getData", parameters);
        Page<Map<String, Object>> result = commonService.paginateByXml(page,limit,"Dynasearch_"+sqlid+"_count", "Dynasearch_"+sqlid+"_getData", parameters);
        setJson(result);
    }
}
