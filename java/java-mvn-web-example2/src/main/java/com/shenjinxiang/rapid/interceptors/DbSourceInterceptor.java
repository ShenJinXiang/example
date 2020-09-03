package com.shenjinxiang.rapid.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.StrKit;
import com.shenjinxiang.rapid.annotations.MyBatisDbConn;
import com.shenjinxiang.rapid.annotations.MyBatisDbConnTx;
import com.shenjinxiang.rapid.consts.RapidConsts;
import com.shenjinxiang.rapid.exceptions.BizException;
import com.shenjinxiang.rapid.exceptions.RapidException;
import com.shenjinxiang.rapid.plugin.mybatis.MyBatisSessionManager;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class DbSourceInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(DbSourceInterceptor.class);

    @Override
    public void intercept(Invocation invocation) {
        Method method = invocation.getMethod();
        Class clazz = method.getDeclaringClass();

        if (method.isAnnotationPresent(MyBatisDbConnTx.class)) {
            MyBatisDbConnTx myBatisDbConnTx = method.getAnnotation(MyBatisDbConnTx.class);
            generateMyBatisDbConnTx(invocation, myBatisDbConnTx);
            return;
        }
        if (method.isAnnotationPresent(MyBatisDbConn.class)) {
            MyBatisDbConn myBatisDbConn = method.getAnnotation(MyBatisDbConn.class);
            generateMyBatisDbConn(invocation, myBatisDbConn);
            return;
        }
        if (clazz.isAnnotationPresent(MyBatisDbConnTx.class)) {
            MyBatisDbConnTx myBatisDbConnTx = (MyBatisDbConnTx) clazz.getAnnotation(MyBatisDbConnTx.class);
            generateMyBatisDbConnTx(invocation, myBatisDbConnTx);
            return;
        }
        if (clazz.isAnnotationPresent(MyBatisDbConn.class)) {
            MyBatisDbConn myBatisDbConn = (MyBatisDbConn) clazz.getAnnotation(MyBatisDbConn.class);
            generateMyBatisDbConn(invocation, myBatisDbConn);
            return;
        }
        invocation.invoke();
    }

    private void generateMyBatisDbConn(Invocation invocation, MyBatisDbConn myBatisDbConn) {
        boolean createSession = false;
        SqlSession session = MyBatisSessionManager.getSession();
        String dbSourceKey = RapidConsts.getDEFAULT_DBSOURCE_KEY();
        if (null == session) {
            dbSourceKey = StrKit.isBlank(myBatisDbConn.value()) ?
                    RapidConsts.getDEFAULT_DBSOURCE_KEY() :
                    myBatisDbConn.value();
            MyBatisSessionManager.setSession(dbSourceKey, true);
            createSession = true;
            doLog("已获取数据库连接，数据库key：" + dbSourceKey);
        }
        try {
            if (createSession) {
                doLog("准备执行业务...");
            }
            invocation.invoke();
            if (createSession) {
                doLog("业务已执行...");
            }

        } catch (BizException e) {
//            MyBatisSessionManager.rollback();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RapidException(e.getMessage());
        } finally {
            if (createSession) {
                MyBatisSessionManager.closeSession();
                doLog("连接已释放，数据库key：" + dbSourceKey);
            }
        }
    }

    private void generateMyBatisDbConnTx(Invocation invocation, MyBatisDbConnTx myBatisDbConnTx) {
        boolean createSession = false;
        SqlSession session = MyBatisSessionManager.getSession();
        String dbSourceKey = RapidConsts.getDEFAULT_DBSOURCE_KEY();
        if (null == session) {
            dbSourceKey = StrKit.isBlank(myBatisDbConnTx.value()) ?
                    RapidConsts.getDEFAULT_DBSOURCE_KEY() :
                    myBatisDbConnTx.value();
            MyBatisSessionManager.setSession(dbSourceKey, false);
            createSession = true;
            doLog("已获取数据库连接，数据库key：" + dbSourceKey);
            doLog("数据库连接事务已开启...");
        }
        try {
            if (createSession) {
                doLog("准备执行业务...");
            }
            invocation.invoke();
            if (createSession) {
                doLog("业务已执行...");
                MyBatisSessionManager.commit();
                doLog("事务已提交...");
            }

        } catch (BizException e) {
            MyBatisSessionManager.rollback();
            doLog("事务已回滚...");
            doLog("BizException: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            MyBatisSessionManager.rollback();
            doLog("事务已回滚...");
            e.printStackTrace();
            throw new RapidException(e.getMessage());
        } finally {
            if (createSession) {
                MyBatisSessionManager.closeSession();
                doLog("连接已释放，数据库key：" + dbSourceKey);
            }
        }
    }

    private void doLog(String message) {
        if (RapidConsts.IS_DEV_MODE) {
            logger.info(message);
        }
    }
}
