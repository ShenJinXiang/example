package com.shenjinxiang.mvn.rapid.shiro;


import com.jfinal.aop.Aop;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.ext.plugin.shiro.ShiroMethod;
import com.shenjinxiang.mvn.services.xtwh.ZyglService;

import java.util.List;

/**
 * 权限拦截器
 * @author liugong
 *
 */
public class RapidShiroInterceptor implements Interceptor {


//	@Inject
//	private ZyglService zyglService;
	private static List<String> urls = null;
	private static boolean isResetUrls = false;


	@Override
	public void intercept(Invocation ai) {
		//获取当前url
		String url = ai.getActionKey();
		try
		{
			if (urls == null) {
			    ZyglService zyglService = Aop.get(ZyglService.class);
				urls = zyglService.queryAllZylj();
			}

			if (urls.contains(url) && !ShiroMethod.hasPermission(url)) {
				ai.getController().renderError(401);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			ai.getController().renderError(401);
		}

		ai.invoke();
		//是否重置系统url
		if (isResetUrls) {
			urls = null;
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
