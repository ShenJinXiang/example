package com.shenjinxiang.mvn.rapid.domain;

/**
 * 绑定当前登录用户
 * @author Administrator
 *
 */
public class CurrentRyxx extends Bean{

	private static final long serialVersionUID = 2254392222660598447L;

	public String getRydm() {
		return this.getStr("rydm");
	}

	public String getRymc() {
		return this.getStr("rymc");
	}
	
	public String getDh() {
		return this.getStr("dh");
	}

	public String getSsjgid() {
		return this.getStr("ssjgid");
	}
	
	public String getSsjgmc(){
		return this.getStr("ssjgmc");
	}
	
	public String getSsgsid() {
		return this.getStr("ssgsid");
	}
	
	public String getSsgsmc(){
		return this.getStr("ssgsmc");
	}

	public String getScsjqx() {
		return this.getStr("scsjqx");
	}

	public String getXssjqx() {
		return this.getStr("xssjqx");
	}

}
