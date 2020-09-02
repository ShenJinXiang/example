package com.shenjinxiang.mvn.rapid.exceptions;

/**
 * Rapid平台业务异常
 * @author Administrator
 */
public class BizException extends RuntimeException { 

	private static final long serialVersionUID = 1778611900103215005L;

	public BizException(String message) {
		super(message);
	}
}
