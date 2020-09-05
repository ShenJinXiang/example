package com.shenjinxiang.mvn.services.xtwh;

import com.shenjinxiang.mvn.mapper.xtwh.ZyglMapper;
import com.shenjinxiang.mvn.rapid.annotations.MyBatisDbConn;
import com.shenjinxiang.mvn.rapid.annotations.MyBatisDbConnTx;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.exceptions.BizException;
import com.shenjinxiang.mvn.rapid.plugin.mybatis.MyBatisSessionManager;

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
}
