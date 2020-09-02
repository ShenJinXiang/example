package com.shenjinxiang.mvn.rapid.shiro;


import cn.dreampie.shiro.core.ShiroMethod;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 权限拦截器
 * @author liugong
 *
 */
public class RapidShiroInterceptor implements Interceptor {
	

	private static boolean isResetUrls = false;


	@Override
	public void intercept(Invocation ai) {
		//获取当前url
		String url = ai.getActionKey();
		try
		{
			//url权限判断
//			if (!ShiroMethod.hasPermission(url)){
				//无权限
				ai.getController().renderError(401);
//			}
		} catch (Exception e)
		{
			e.printStackTrace();
			ai.getController().renderError(401);
		}
		ai.invoke();
		//是否重置系统url
		if (isResetUrls) {
			isResetUrls = false;
		}
	}
	
	/**
	 * 重置URL
	 */
	public static void resetUrl(){
		isResetUrls = true;
	}

}
