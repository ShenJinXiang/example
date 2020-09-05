package com.shenjinxiang.mvn.rapid.actions;

import com.jfinal.core.Controller;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.domain.CurrentRyxx;

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
	
	/**
	 * 获取登陆用户
	 */
	protected CurrentRyxx getCurrentRyxx(){
		CurrentRyxx currentRyxx = getSessionAttr("currentRyxx");
		return currentRyxx;
	}
	
	/**
	 * 绑定当前登录用户到页面
	 */
	protected void bindCurrentRyxxToPage() {
		setAttr("currentRyxx", getCurrentRyxx());
	}
	
	/**
	 * 绑定当前登录用户到bean
	 * @param bean
	 */
	protected void bindCurretRyxxToBean(Bean bean) {
		CurrentRyxx currentRyxx = getCurrentRyxx();
		if (null == currentRyxx) {
			return;
		}
		bean.set("currentRyxx_ryid", currentRyxx.getRyid());
		bean.set("currentRyxx_rybh", currentRyxx.getRybh());
		bean.set("currentRyxx_ryzh", currentRyxx.getRyzh());
		bean.set("currentRyxx_rymc", currentRyxx.getRymc());
		bean.set("currentRyxx_lxdh", currentRyxx.getLxdh());
		bean.set("currentRyxx_ssbmid", currentRyxx.getSsbmid());
		bean.set("currentRyxx_ssbmmc", currentRyxx.getSsbmmc());
		bean.set("currentRyxx_sfmr", currentRyxx.getSfmr());
		bean.set("currentRyxx_bz", currentRyxx.getBz());
	}
	

	/**
	 * 绑定当前登录用户到map
	 * @param parameters
	 */
	protected void bindCurrentRyxxToMap(Map<String, Object> parameters) {
		CurrentRyxx currentRyxx = getCurrentRyxx();
		if (null == currentRyxx) {
			return;
		}
		parameters.put("currentRyxx_ryid", currentRyxx.getRyid());
		parameters.put("currentRyxx_rybh", currentRyxx.getRybh());
		parameters.put("currentRyxx_ryzh", currentRyxx.getRyzh());
		parameters.put("currentRyxx_rymc", currentRyxx.getRymc());
		parameters.put("currentRyxx_lxdh", currentRyxx.getLxdh());
		parameters.put("currentRyxx_ssbmid", currentRyxx.getSsbmid());
		parameters.put("currentRyxx_ssbmmc", currentRyxx.getSsbmmc());
		parameters.put("currentRyxx_sfmr", currentRyxx.getSfmr());
		parameters.put("currentRyxx_bz", currentRyxx.getBz());
	}
}
