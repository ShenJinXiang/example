package com.shenjinxiang.mvn.rapid.domain;

/**
 * 绑定当前登录用户
 * @author Administrator
 *
 */
public class CurrentRyxx extends Bean{

	private static final long serialVersionUID = 2254392222660598447L;

	public int getRyid() {
		return this.getInt("ryid");
	}

	public String getRybh() {
		return this.getStr("rybh");
	}

	public String getRyzh() {
		return this.getStr("ryzh");
	}

	public String getRymc() {
		return this.getStr("rymc");
	}

	public String getLxdh() {
		return this.getStr("lxdh");
	}

	public int getSsbmid() {
		return this.getInt("ssbmid");
	}

	public int getSsbmmc() {
		return this.getInt("ssbmmc");
	}

	public boolean getSfmr() {
		return this.getBoolean("sfmr");
	}

	public String getBz() {
		return this.getStr("bz");
	}

}
