package com.shenjinxiang.rapid.domain;

import java.util.HashMap;
import java.util.HashSet;

public class Bean extends HashMap<String, Object>{
	
	private static final long serialVersionUID = 5148441654239456772L;
	
	private HashSet<String> modifyFlag = new HashSet<String>();	//试用set方法时，标示set过的字段是要被修改的

	public HashSet<String> getModifyFlag() {
		return modifyFlag;
	}

	public Bean set(String key,Object value){
		this.put(key, value);
		modifyFlag.add(key);
		return this;
	}
	
	public Bean clear(String key){
		this.remove(key);
		return this;
	}
	
	public String getStr(String attr) {
		return (String)this.get(attr);
	}
	
	public Integer getInt(String attr) {
		return (Integer)this.get(attr);
	}
	
	public Long getLong(String attr) {
		return (Long)this.get(attr);
	}
	
	public java.math.BigInteger getBigInteger(String attr) {
		return (java.math.BigInteger)this.get(attr);
	}
	
	public java.util.Date getDate(String attr) {
		return (java.util.Date)this.get(attr);
	}
	
	public java.sql.Time getTime(String attr) {
		return (java.sql.Time)this.get(attr);
	}
	
	public java.sql.Timestamp getTimestamp(String attr) {
		return (java.sql.Timestamp)this.get(attr);
	}
	
	public Double getDouble(String attr) {
		return (Double)this.get(attr);
	}
	
	public Float getFloat(String attr) {
		return (Float)this.get(attr);
	}
	
	public Boolean getBoolean(String attr) {
		return (Boolean)this.get(attr);
	}
	
	public java.math.BigDecimal getBigDecimal(String attr) {
		return (java.math.BigDecimal)this.get(attr);
	}
	
	public byte[] getBytes(String attr) {
		return (byte[])this.get(attr);
	}
	
	public Number getNumber(String attr) {
		return (Number)this.get(attr);
	}	
	
}
