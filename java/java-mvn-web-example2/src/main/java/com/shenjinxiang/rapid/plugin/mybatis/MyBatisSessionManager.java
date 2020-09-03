package com.shenjinxiang.rapid.plugin.mybatis;

import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

/**
 * Created by shenjinxiang on 2017-11-29.
 */
public class MyBatisSessionManager {
	
	private static ThreadLocal<SqlSession> sqlSessionHolder = new ThreadLocal<SqlSession>();

	/**
	 * 获取当前线程内sqlsession
	 * @return
	 */
	public static SqlSession getSession(){
		SqlSession session = sqlSessionHolder.get();
		return session;
	}
	
	/**
	 * 将sqlsession保存到当前线程容器内
	 * @throws SQLException 
	 */
	public static void setSession(){
		SqlSession session = MyBatisPlugin.getSqlSessionFactory().openSession();
		sqlSessionHolder.set(session); 
	}

	public static SqlSession setSession(String dbSourceKey) {
		SqlSession session = MyBatisPlugin.getSqlSessionFactory(dbSourceKey).openSession();
		sqlSessionHolder.set(session);
		return session;
	}

	
	/**
	 * 将sqlsession保存到当前线程容器内
	 * @throws SQLException 
	 */
	public static void setSession(boolean isTx){
		SqlSession session = MyBatisPlugin.getSqlSessionFactory().openSession(isTx);
		sqlSessionHolder.set(session); 
	}

	public static SqlSession setSession(String dbSourceKey, boolean isTx){
		SqlSession session = MyBatisPlugin.getSqlSessionFactory(dbSourceKey).openSession(isTx);
		sqlSessionHolder.set(session);
		return session;
	}
	
	/**
	 * 关闭sqlsession并从线程容器内释放
	 */
	public static void closeSession(){
		SqlSession session = sqlSessionHolder.get();
		if (session!=null) {
			session.close();
			sqlSessionHolder.remove();
		}
	}
	
	/**
	 * 提交事务
	 */
	public static void commit(){
		SqlSession session = sqlSessionHolder.get();
		if (session!=null) {
			session.commit(true);
		}
		
	}
	
	/**
	 * 回滚事务
	 */
	public static void rollback(){
		SqlSession session = sqlSessionHolder.get();
		if (session!=null) {
			session.rollback(true);
		}
	}

	public static <T> T getMapper(Class<T> clazz) {
		return getSession().getMapper(clazz);
	}
	
}
