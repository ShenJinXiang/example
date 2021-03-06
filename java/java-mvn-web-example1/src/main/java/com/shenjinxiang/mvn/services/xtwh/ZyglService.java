package com.shenjinxiang.mvn.services.xtwh;

import com.shenjinxiang.mvn.mapper.xtwh.ZyglMapper;
import com.shenjinxiang.mvn.rapid.annotations.MyBatisDbConn;
import com.shenjinxiang.mvn.rapid.annotations.MyBatisDbConnTx;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.domain.CurrentRyxx;
import com.shenjinxiang.mvn.rapid.exceptions.BizException;
import com.shenjinxiang.mvn.rapid.plugin.mybatis.MyBatisSessionManager;

import java.util.Iterator;
import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 23:21
 */
public class ZyglService {

    @MyBatisDbConnTx
    public void saveZyxx(Bean zyxx) {
        ZyglMapper zyglMapper = MyBatisSessionManager.getMapper(ZyglMapper.class);
        String sjzyid = zyxx.getStr("sjzyid");
        String zyid = zyglMapper.queryZyid(sjzyid);
        zyxx.set("zyid", zyid);
        if (zyxx.get("zypx") == null || "".equals(zyxx.getStr("zypx").trim())) {
            zyxx.set("zypx", Integer.parseInt(zyid.substring(zyid.length() - 2)));
        }
        zyglMapper.saveZyxx(zyxx);
    }

    @MyBatisDbConn
    public Bean queryZyxx(String zyid) {
        ZyglMapper zyglMapper = MyBatisSessionManager.getMapper(ZyglMapper.class);
        return zyglMapper.selectZyxx(zyid);
    }

    @MyBatisDbConnTx
    public void delete(String zyid) {
        ZyglMapper zyglMapper = MyBatisSessionManager.getMapper(ZyglMapper.class);
        int count = zyglMapper.countByZySjjyid(zyid);
        if (count > 0) {
            throw new BizException("删除失败，存在下级资源！");
        } else {
            // todo 删除角色权限信息
            zyglMapper.deleteByZyid(zyid);
        }
    }

    @MyBatisDbConnTx
    public void updateZyxx(Bean zyxx) {
        ZyglMapper zyglMapper = MyBatisSessionManager.getMapper(ZyglMapper.class);
        zyxx.remove("sjzyid");
        zyglMapper.updateByZyid(zyxx);
    }

    @MyBatisDbConn
    public List<Bean> queryZyxxForTree() {
        ZyglMapper zyglMapper = MyBatisSessionManager.getMapper(ZyglMapper.class);
        List<Bean> list = zyglMapper.queryZyxxForTree();
        return list;
    }

    @MyBatisDbConn
    public List<Bean> queryJsZy(int jsid) {
        ZyglMapper zyglMapper = MyBatisSessionManager.getMapper(ZyglMapper.class);
        List<Bean> list = zyglMapper.queryJsZy(jsid);
        return list;
    }

    @MyBatisDbConn
    public List<Bean> queryZyljForMain(CurrentRyxx currentRyxx) {
        ZyglMapper zyglMapper = MyBatisSessionManager.getMapper(ZyglMapper.class);
        Bean params = new Bean();
        params.set("ryid", currentRyxx.getRyid());
        params.set("sfmr", currentRyxx.getSfmr());
        List<Bean> yjZylist = zyglMapper.queryZyListBySjzy(params);
        Iterator<Bean> iterator = yjZylist.iterator();
        while (iterator.hasNext()) {
            Bean yjzy = iterator.next();
            params.set("sjzyid", yjzy.getStr("zyid"));
            List<Bean> ejzyList = zyglMapper.queryZyListBySjzy(params);
            yjzy.set("ejzySize", ejzyList.size());
            yjzy.set("ejzyList", ejzyList);
        }
        return yjZylist;
    }

    @MyBatisDbConn
    public List<String> queryZyljByRyid(Bean ryxx) {
        ZyglMapper zyglMapper = MyBatisSessionManager.getMapper(ZyglMapper.class);
        List<String> list = zyglMapper.queryZyljByRyid(ryxx);
        return list;
    }

    @MyBatisDbConn
    public List<String> queryAllZylj() {
        ZyglMapper zyglMapper = MyBatisSessionManager.getMapper(ZyglMapper.class);
        List<String> list = zyglMapper.queryAllZylj();
        return list;
    }
}
