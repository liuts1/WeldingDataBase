package com.gy.utils.result;

import java.io.Serializable;
import java.util.List;

import com.gy.user.entity.UserEntity;

/**
 * 接口结果描述对象，所有的接口返回结果中，均应包含此结果。
 * @author kangchao1
 *
 */
public class ResultDto<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 366558166458191244L;
	
	/**
	 * 结果状态，仅包含-1,0,1两个值
	 * -1：异常
	 * 0：逻辑校验不通过，如数据不存咋，请求非法等。
	 * 1：成功
	 */
	private int code;
	
	/**
	 * 错误具体描述，一般常用异常描述以及逻辑校验不通过两种情况
	 */
	private List<FailDesc> failDescList;
	/**
	 * 错误具体描述，一般常用异常描述以及逻辑校验不通过两种情况
	 */
	private String erroeMessage;
	
	/**
	 * 成功后的数据结果
	 */
	private T successData;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<FailDesc> getFailDescList() {
		return failDescList;
	}

	public void setFailDescList(List<FailDesc> failDescList) {
		this.failDescList = failDescList;
	}
	
	public String getErroeMessage() {
		return erroeMessage;
	}

	public void setErroeMessage(String erroeMessage) {
		this.erroeMessage = erroeMessage;
	}

	public static <T> ResultDto<T> buildSuccessResult(T successData){
		ResultDto<T> result = new ResultDto<T>();
		result.setCode(1);
		result.setSuccessData(successData);
		return result;
	}
	
	public static <T> ResultDto<T> buildFailResult(){
		ResultDto<T> result = new ResultDto<T>();
		result.setCode(0);
		return result;
	}
	
	public static <T> ResultDto<T> buildFailResult(String errorMessage1){
		ResultDto<T> result = new ResultDto<T>();
		result.setCode(0);
		result.setErroeMessage(errorMessage1);
		return result;
	}

	public T getSuccessData() {
		return successData;
	}

	public void setSuccessData(T successData) {
		this.successData = successData;
	}
	
	public boolean isSuccess(){
		return this.code == 1;
	}

	public static <T> ResultDto<T> buildFailResult(String string, int i) {
		ResultDto<T> result = new ResultDto<T>();
		result.setCode(i);
		result.setErroeMessage(string);
		return result;
	}

}
