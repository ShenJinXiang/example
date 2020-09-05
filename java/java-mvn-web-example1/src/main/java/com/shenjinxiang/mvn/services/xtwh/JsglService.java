package com.shenjinxiang.mvn.services.xtwh;

import com.shenjinxiang.mvn.mapper.xtwh.JsglMapper;
import com.shenjinxiang.mvn.rapid.annotations.MyBatisDbConn;
import com.shenjinxiang.mvn.rapid.annotations.MyBatisDbConnTx;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.exceptions.BizException;
import com.shenjinxiang.mvn.rapid.plugin.mybatis.MyBatisSessionManager;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/5 23:58
 */
public class JsglService {

    @MyBatisDbConn
    public String queryJsbh() {
        JsglMapper jsglMapper = MyBatisSessionManager.getMapper(JsglMapper.class);
        return jsglMapper.queryJsbh();
    }


    @MyBatisDbConnTx
    public void saveJsxx(Bean params) {
        JsglMapper jsglMapper = MyBatisSessionManager.getMapper(JsglMapper.class);
        int count = jsglMapper.countByJsmc(params);
        if (count > 0) {
            throw new BizException("保存失败，重复的角色名称！");
        }
        String jsbh = jsglMapper.queryJsbh();
        params.set("jsbh", jsbh);
        jsglMapper.insertJsxx(params);
    }

    @MyBatisDbConnTx
    public void updateJsxx(Bean params) {
        JsglMapper jsglMapper = MyBatisSessionManager.getMapper(JsglMapper.class);
        int count = jsglMapper.countByJsmc(params);
        if (count > 0) {
            throw new BizException("保存失败，重复的角色名称！");
        }
        jsglMapper.updateJsxx(params);
    }

    @MyBatisDbConn
    public Bean queryJsxx(int jsid) {
        JsglMapper jsglMapper = MyBatisSessionManager.getMapper(JsglMapper.class);
        Bean jsxx = jsglMapper.queryJsxx(jsid);
        return jsxx;
    }

    @MyBatisDbConnTx
    public void deleteJsxx(int jsid) {
        JsglMapper jsglMapper = MyBatisSessionManager.getMapper(JsglMapper.class);
        int count = jsglMapper.countRyJsByJsid(jsid);
        if (count > 0) {
            throw new BizException("删除失败，该角色已被授权！");
        }
        jsglMapper.deleteJsxx(jsid);
        jsglMapper.deleteJsqx(jsid);
    }
}
