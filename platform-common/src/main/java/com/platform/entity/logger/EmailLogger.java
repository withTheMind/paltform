package com.platform.entity.logger;

import com.alibaba.fastjson.JSON;

/**
 * 邮箱修改时日志记录实体
 */
public class EmailLogger {
	
	private String phone;//账户
	private String oldEmail;//就邮箱
	private String newEmail;//新 邮箱
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOldEmail() {
		return oldEmail;
	}
	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}
	public String getNewEmail() {
		return newEmail;
	}
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
