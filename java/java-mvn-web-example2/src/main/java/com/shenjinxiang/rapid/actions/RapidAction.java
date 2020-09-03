package com.shenjinxiang.rapid.actions;

import com.jfinal.core.Controller;
import com.shenjinxiang.rapid.domain.Bean;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**s
 * Rapid平台Action类，开发时继承于它
 * @author liugong
 *
 */
public abstract class RapidAction extends Controller {
	
	/**
	 * 获取所有请求中的参数并封装成MAP
	 * @return
	 */
	protected Map<String, Object> getParams() { 
		HttpServletRequest request = getRequest();
		Map<String, Object> params = new HashMap<String, Object>(); 
		for (Enumeration<String> iter = request.getParameterNames(); iter.hasMoreElements();) {
			String paraName = (String) iter.nextElement();
			params.put(paraName, request.getParameter(paraName));
		}
		return params;
	}

	/**
	 * 获取所有请求中的参数并封装成MAP
	 * @return
	 */
	protected Bean getBean() {
		HttpServletRequest request = getRequest();
		Bean bean = new Bean();
		for (Enumeration<String> iter = request.getParameterNames(); iter.hasMoreElements();) {
			String paraName = (String) iter.nextElement();
			bean.put(paraName, request.getParameter(paraName));
		}
		return bean;
	}
	/**
	 * 清除无效的存库参数（用于公用的save和update）
	 * @param parameters
	 * @param parNames
	 */
	public static void clearPar(HashMap<String, Object> parameters,String... parNames){
		for (String string : parNames) {
			parameters.remove(string);
		}
	}

	/**
	 * 设置json结果，用于下级拦截器返回客户端
	 * @param object
	 */
	public void setJson(Object object){
		setAttr("result", object);
	}
	
}
