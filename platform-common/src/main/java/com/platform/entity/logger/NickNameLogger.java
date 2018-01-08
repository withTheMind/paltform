package com.platform.entity.logger;

import com.alibaba.fastjson.JSON;

/**
 * 昵称修改日志记录实体
 */
public class NickNameLogger {
	
	private String phone;//账户
	private String oldNickName;//原来昵称
	private String newNickName;//现有昵称
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOldNickName() {
		return oldNickName;
	}
	public void setOldNickName(String oldNickName) {
		this.oldNickName = oldNickName;
	}
	public String getNewNickName() {
		return newNickName;
	}
	public void setNewNickName(String newNickName) {
		this.newNickName = newNickName;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
