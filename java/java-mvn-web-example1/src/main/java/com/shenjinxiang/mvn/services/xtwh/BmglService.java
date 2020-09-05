package com.shenjinxiang.mvn.services.xtwh;

import com.shenjinxiang.mvn.mapper.xtwh.BmglMapper;
import com.shenjinxiang.mvn.mapper.xtwh.RyglMapper;
import com.shenjinxiang.mvn.rapid.annotations.MyBatisDbConn;
import com.shenjinxiang.mvn.rapid.annotations.MyBatisDbConnTx;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.exceptions.BizException;
import com.shenjinxiang.mvn.rapid.plugin.mybatis.MyBatisSessionManager;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/5 22:09
 */
public class BmglService {


    /**
     * 新增时获取部门编号
     * @return
     */
    @MyBatisDbConn
    public String queryBmbh() {
        BmglMapper bmglMapper = MyBatisSessionManager.getMapper(BmglMapper.class);
        return bmglMapper.queryBmbh();
    }

    /**
     * 新增
     * @param params
     */
    @MyBatisDbConnTx
    public void saveBmxx(Bean params) {
        BmglMapper bmglMapper = MyBatisSessionManager.getMapper(BmglMapper.class);
        String bmid = bmglMapper.queryBmid(params.getStr("sjbmid"));
        String bmbh = bmglMapper.queryBmbh();
        params.set("bmid", bmid);
        params.set("bmbh", bmbh);
        bmglMapper.insertBmxx(params);

    }

    /**
     * 修改
     * @param params
     */
    @MyBatisDbConnTx
    public void updateBmxx(Bean params) {
        BmglMapper bmglMapper = MyBatisSessionManager.getMapper(BmglMapper.class);
        bmglMapper.updateBmxx(params);
    }

    @MyBatisDbConn
    public Bean queryBmxx(String bmid) {
        BmglMapper bmglMapper = MyBatisSessionManager.getMapper(BmglMapper.class);
        Bean bmxx = bmglMapper.queryBmxx(bmid);
        return bmxx;
    }

    @MyBatisDbConnTx
    public void deleteBmxx(String bmid) {
        BmglMapper bmglMapper = MyBatisSessionManager.getMapper(BmglMapper.class);
        int childCount = bmglMapper.countBySjbmid(bmid);
        if (childCount > 0) {
            throw new BizException("存在下级部门，不能删除！");
        } else {
            RyglMapper ryglMapper = MyBatisSessionManager.getMapper(RyglMapper.class);
            int rycount = ryglMapper.countBySsbmid(bmid);
            if (rycount > 0) {
                throw new BizException("删除失败，部门下存在人员信息！");
            }
            bmglMapper.deleteByBmid(bmid);
        }
    }
}
