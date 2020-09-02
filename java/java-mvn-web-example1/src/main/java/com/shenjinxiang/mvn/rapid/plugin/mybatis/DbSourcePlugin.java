package com.shenjinxiang.mvn.rapid.plugin.mybatis;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Plugins;
import com.jfinal.kit.StrKit;
import com.shenjinxiang.mvn.rapid.consts.RapidConsts;
import com.shenjinxiang.mvn.rapid.kits.XMLUtil;
import com.shenjinxiang.mvn.rapid.plugin.druid.DruidPlugin;
import org.apache.ibatis.io.Resources;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shenjinxiang on 2017-11-29.
 */
public class DbSourcePlugin {

    public static void configPlugin(Plugins plugins) {
        try {
            Map<String, Object> dbMap = readDbSourceXMl();
            String defaultKey = (String) dbMap.get("defaultKey");
            RapidConsts.setDEFAULT_DBSOURCE_KEY(defaultKey);
            Object dbSourceData = dbMap.get("dbSource");
            if (null == dbSourceData) {
                throw new IllegalArgumentException("数据库配置错误：没有配置任何数据库信息");
            }
            List<Map<String, Object>> dbSourceList = new ArrayList<>();
            if (dbSourceData instanceof List) {
                dbSourceList = (List<Map<String, Object>>) dbSourceData;
            } else {
                dbSourceList.add((Map<String, Object>) dbSourceData);
            }
            boolean checkDefaultKey = false;
            if (null != dbSourceList) {
                for (Map<String, Object> dbSource : dbSourceList) {
                    String key = (String) dbSource.get("key");
                    if (StrKit.notBlank(defaultKey) && defaultKey.equals(key)) {
                        checkDefaultKey = true;
                    }
                    configDb(dbSource, plugins);
                }
                MyBatisPlugin.setShowSql(RapidConsts.IS_DEV_MODE);
            }
            if (!checkDefaultKey) {
                throw new IllegalArgumentException("数据库配置错误：dbSources未找到与defaultKey对应的配置信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void configDb(Map<String, Object> dbSource, Plugins plugins) {
        String key = (String) dbSource.get("key");
        String dbType = (String) dbSource.get("dbType");
        String driver = (String) dbSource.get("driver");
        String jdbcUrl = (String) dbSource.get("jdbcUrl");
        String user = (String) dbSource.get("user");
        String password = (String) dbSource.get("password");
        int dbMaxActive = Integer.valueOf((String)dbSource.get("dbMaxActive"));
        int dbInitSize = Integer.valueOf((String)dbSource.get("dbInitSize"));

        DruidPlugin dp = new DruidPlugin(jdbcUrl, user, password);
        dp.setDriverClass(driver);
        dp.setDbType(dbType);
        dp.addFilter(new StatFilter());
        dp.setMaxActive(dbMaxActive);
        if (RapidConsts.IS_DEV_MODE) {
            dp.setInitialSize(1);
        }else {
            dp.setInitialSize(dbInitSize);
        }
        WallFilter wall = new WallFilter();
        wall.setDbType(dbType);
        dp.addFilter(wall);
        plugins.add(dp);
        MyBatisPlugin myBatisPlugin = new MyBatisPlugin(dp, key);
        plugins.add(myBatisPlugin);
    }

    private static Map<String,Object> readDbSourceXMl() throws Exception {
        String src = "dbConfig.xml";
        if (RapidConsts.IS_DEV_MODE) {
            src = "dbConfig_dev.xml";
        }
        Reader reader = Resources.getResourceAsReader(src);
        if (null == reader) {
            throw new IllegalArgumentException("没找到数据库配置文件：" + src);
        }
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String text = stringBuilder.toString();
        Map<String, Object> dbMap = XMLUtil.text2Map(text);
        reader.close();
        return dbMap;
    }

}
