package com.shsxt.model.vo;


/**
 * 返回的封装类
 * @author Lisa Li  
 * @date 2017年8月21日
 */
public class ResultInfo {
	
	private Integer resultCode; // 状态码 1=成功，0=失败
	private String resultMessage;  // 消息提示
	private Object resultObject; // 返回的对象
	private Integer resultID; // 返回的ID
	
	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public Object getResultObject() {
		return resultObject;
	}
	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}
	public Integer getResultID() {
		return resultID;
	}
	public void setResultID(Integer resultID) {
		this.resultID = resultID;
	}
	
}
