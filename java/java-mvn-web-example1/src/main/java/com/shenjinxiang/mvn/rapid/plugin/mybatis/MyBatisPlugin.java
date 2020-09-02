package com.shenjinxiang.mvn.rapid.plugin.mybatis;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.IPlugin;
import com.shenjinxiang.mvn.rapid.consts.RapidConsts;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenjinxiang on 2017-11-29.
 */
public class MyBatisPlugin implements IPlugin {

	private static Map<String, SqlSessionFactory> sqlSessionFactoryMap = new HashMap();
	private static Reader reader;
	private String dbSourceKey = RapidConsts.getDEFAULT_DBSOURCE_KEY();

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactoryMap.get(RapidConsts.getDEFAULT_DBSOURCE_KEY());
	}

	public static SqlSessionFactory getSqlSessionFactory(String dbSourceKey) {
		if(StrKit.isBlank(dbSourceKey)){
			return sqlSessionFactoryMap.get(RapidConsts.getDEFAULT_DBSOURCE_KEY());
		}else{
			return sqlSessionFactoryMap.get(dbSourceKey);
		}
	}

	private DataSourceFactory ds = null;
	
	public MyBatisPlugin(DataSourceFactory ds){
		this.ds = ds;
		this.dbSourceKey = RapidConsts.getDEFAULT_DBSOURCE_KEY();
	}

	public MyBatisPlugin(DataSourceFactory ds, String dbSourceKey) {
		this.dbSourceKey = dbSourceKey;
		this.ds = ds;
	}

	@Override
	public boolean start() {
		try {
			System.out.println("开启数据源：" + dbSourceKey);
			reader = Resources.getResourceAsReader("mybatis/Configuration.xml");
			SqlSessionFactory _sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			Environment environment = new Environment("druid", new JdbcTransactionFactory(), ds.getDataSource());
			_sqlSessionFactory.getConfiguration().setEnvironment(environment);
			sqlSessionFactoryMap.put(dbSourceKey, _sqlSessionFactory);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}  
		return true;
	}

	@Override
	public boolean stop() {
		sqlSessionFactoryMap = null;
		return true;
	}
	
	/**
	 * 是否打印sql
	 * @param isShowSql
	 */
	public static void setShowSql(boolean isShowSql){
		if (isShowSql) {
			org.apache.ibatis.logging.LogFactory.useSlf4jLogging(); 
			org.apache.ibatis.logging.LogFactory.useJdkLogging();
			org.apache.ibatis.logging.LogFactory.useStdOutLogging();
		}
	}

}
