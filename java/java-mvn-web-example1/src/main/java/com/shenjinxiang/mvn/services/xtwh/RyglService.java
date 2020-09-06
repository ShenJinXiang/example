package com.shenjinxiang.mvn.services.xtwh;

import com.shenjinxiang.mvn.mapper.xtwh.RyglMapper;
import com.shenjinxiang.mvn.rapid.annotations.MyBatisDbConn;
import com.shenjinxiang.mvn.rapid.annotations.MyBatisDbConnTx;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.exceptions.BizException;
import com.shenjinxiang.mvn.rapid.kits.StringUtil;
import com.shenjinxiang.mvn.rapid.plugin.mybatis.MyBatisSessionManager;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/6 14:53
 */
public class RyglService {

    @MyBatisDbConn
    public String queryRybh() {
        RyglMapper ryglMapper = MyBatisSessionManager.getMapper(RyglMapper.class);
        return ryglMapper.queryRybh();
    }

    @MyBatisDbConn
    public Bean queryRyxx(int ryid) {
        RyglMapper ryglMapper = MyBatisSessionManager.getMapper(RyglMapper.class);
        return ryglMapper.queryRyxx(ryid);
    }

    @MyBatisDbConnTx
    public void saveRyxx(Bean params) {
        RyglMapper ryglMapper = MyBatisSessionManager.getMapper(RyglMapper.class);
        int count = ryglMapper.countByRyzh(params);
        if (count > 0) {
            throw new BizException("保存失败，重复的用户账号！");
        }
        params.set("mm", StringUtil.getMD5_SALT("123456"));
        String rybh = ryglMapper.queryRybh();
        params.set("rybh", rybh);
        ryglMapper.saveRyxx(params);
    }

    @MyBatisDbConnTx
    public void updateRyxx(Bean params) {
        RyglMapper ryglMapper = MyBatisSessionManager.getMapper(RyglMapper.class);
        int count = ryglMapper.countByRyzh(params);
        if (count > 0) {
            throw new BizException("保存失败，重复的用户账号！");
        }
        ryglMapper.updateRyxx(params);
    }

    @MyBatisDbConnTx
    public void delRyxx(int ryid) {
        RyglMapper ryglMapper = MyBatisSessionManager.getMapper(RyglMapper.class);
        Bean ryxx = ryglMapper.queryRyxx(ryid);
        if (ryxx.getBoolean("sfmr")) {
            throw new BizException("删除失败，无法删除系统默认账户");
        }
        ryglMapper.deleteRyJsxx(ryid);
        ryglMapper.deleteRyxx(ryid);
    }

    @MyBatisDbConnTx
    public void resetPwd(int ryid) {
        RyglMapper ryglMapper = MyBatisSessionManager.getMapper(RyglMapper.class);
        Bean ryxx = new Bean();
        ryxx.set("ryid", ryid);
        ryxx.set("mm", StringUtil.getMD5_SALT("123456"));
        ryglMapper.resetPwd(ryxx);
    }

    @MyBatisDbConnTx
    public void saveRyJs(int ryid, String[] jsidArr) {
        RyglMapper ryglMapper = MyBatisSessionManager.getMapper(RyglMapper.class);
        ryglMapper.deleteRyJsxx(ryid);
        if (null != jsidArr && jsidArr.length > 0) {
            Bean params = new Bean();
            params.set("ryid", ryid);
            params.set("jsids", jsidArr);
            ryglMapper.insertRyjs(params);
        }
    }

    @MyBatisDbConn
    public List<Bean> queryJsForTree(int ryid) {
        RyglMapper ryglMapper = MyBatisSessionManager.getMapper(RyglMapper.class);
        List<Bean> list = ryglMapper.queryJsForTree(ryid);
        return list;
    }
}
