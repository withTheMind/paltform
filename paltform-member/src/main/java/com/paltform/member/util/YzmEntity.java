package com.paltform.member.util;

import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * 发送验证码构造
 * @author Administrator
 *
 */
public class YzmEntity {
	
	private String phone;//接受验证码手机号
	private String yzm;//验证码
	private Date sendTime;//发送时间  有效期30min
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
