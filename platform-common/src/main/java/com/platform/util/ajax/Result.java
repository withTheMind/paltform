package com.platform.util.ajax;

/**
 * ajax请求响应实体
 * @author Administrator
 *
 */
public class Result {
	
	private int status = 500;//200:成功,403:没有权限，500:错误
	private String message;//相关描述
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
