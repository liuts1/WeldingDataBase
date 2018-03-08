package com.gy.utils.result;

import java.io.Serializable;

public class FailDesc implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3921088432817849578L;
	
	/**
	 * 错误编码，failCode采用分级代码方式，每一级采用3位代码，
	 * aaabbbcccddd...
	 * aaa表示所属系统，由项目组确认。具体值为com.nemo.nemoServiceAPI.commons.GlobalCommons.SystemCodes
	 * bbb标示所属接口，系统内顺序编码，建议按照模块进行区分，如将第一位以A-Z,a-z,0-9进行划分，支持62个模块实际上也不会有这么大的范围，而每个模块内部可以支持62*62个接口方法。
	 * ccc标示此接口的错误编码，通常情况下，一个接口方法会有多种错误可能，因此会对应多个错误编码
	 * ddd用于扩展，理论上支持无限扩展，
	 * ccc、ddd为接口使用方和提供方共同定义
	 * 所有分级均采用3位补齐方式，如001代表系统1，而不能使用1代表。
	 */
	private String failCode;
	/**
	 * 错误名称
	 * 如runtimeException可以统一定义为runtimeException。目前不提供通用值
	 */
	private String name;
	/**
	 * 错误描述
	 * 具体错误信息，由提供者和使用者共同制定
	 */
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFailCode() {
		return failCode;
	}

	public void setFailCode(String failCode) {
		this.failCode = failCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
