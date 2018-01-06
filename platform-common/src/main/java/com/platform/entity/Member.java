package com.platform.entity;

import java.io.Serializable;

/**
 * 会员
 * @author Administrator
 *
 */
public class Member implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int memberId;
	private String phone;//手机号 登录账号
	private String password;//密码
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}