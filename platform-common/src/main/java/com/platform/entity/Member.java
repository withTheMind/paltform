package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

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
	private Date registerTime;//注册时间
	private String nickName;//昵称
	private String email;//邮箱
	
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getRegisterTime() {
		return registerTime;
	}

	
}
