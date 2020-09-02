package com.shenjinxiang.mvn.rapid.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.shenjinxiang.mvn.rapid.consts.RapidConsts;
import com.shenjinxiang.mvn.rapid.domain.JsonResult;
import com.shenjinxiang.mvn.rapid.exceptions.BizException;

/**
 * 拦截器，用于提供json格式的输出
 * @author liugong
 *
 */
public class JsonResultInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation ai) {
		JsonResult jsonResult = new JsonResult();
		try {
			ai.invoke();
			jsonResult.setSuccess(true);
			Object result = ai.getController().getAttr("result");
			jsonResult.setMessage(result);
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			if (!(e instanceof BizException)) {
				if (RapidConsts.IS_DEV_MODE) {
					jsonResult.setMessage(e.getMessage());
				}else {
					jsonResult.setMessage(RapidConsts.getErrorMsg());
				}
				e.printStackTrace();
			}else {
				jsonResult.setMessage(e.getMessage());
			}
		}
		ai.getController().renderJson(jsonResult);
	}

}
