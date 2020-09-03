package com.shenjinxiang.rapid.services;

import com.jfinal.plugin.activerecord.Page;
import com.shenjinxiang.rapid.annotations.MyBatisDbConnTx;
import com.shenjinxiang.rapid.plugin.mybatis.MyBatisSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 21:48
 */
public class CommonService {

    @MyBatisDbConnTx
    public Page<Map<String, Object>> paginateByXml(int page, int limit, String countSqlid, String dataSqlid, Map<String, Object> parameters) {
        SqlSession session = MyBatisSessionManager.getSession();
        int start = (page-1)*limit;
        parameters.put("start", start);
        int count = session.selectOne(countSqlid, parameters);
        List<Map<String, Object>> datas = session.selectList(dataSqlid, parameters);
        int totalPage = (int) (count / limit);
        if (count % limit != 0) {
            totalPage++;
        }
        return new Page<Map<String,Object>>(datas, page, limit, totalPage, count);
    }
}
