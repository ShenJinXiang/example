package com.shenjinxiang.mvn.rapid.domain;


/**
 * JsonResult用于返回客户端的ajax请求
 * @author liugong
 *
 */
public class JsonResult {
	
	public JsonResult(){
	}
	
	public JsonResult(boolean success,Object message){
		this.success = success;
		if (message!=null) {
			this.message = message;
		}
	}
	
	private boolean success;	//是否成功
	
	private Object message = "{}";	//返回数据

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		if (message==null) {
			return;
		}
		this.message = message;
	}

}
