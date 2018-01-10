package com.paltform.member.service;

import com.platform.entity.Member;

public interface MemberService {
	
	/**
	 * 修改会员密码
	 * @param member 账号 密码
	 */
	void updatePassword(Member member);
	
	/**
	 * 判断密码是否正确
	 * @param member 手机号
	 * @param pwd 输入的原密码
	 * @return true:相等,false:不相等
	 */
	boolean judgePassword(Member member, String pwd);
	
	/**
	 * 注册
	 * @param member 账号 密码 注册时间
	 */
	void register(Member member);
	
	/**
	 * 判断手机号是否注册
	 * @param phone 手机号
	 * @return true:已注册,false:未注册
	 */
	boolean queryByPhone(String phone);
	
	/**
	 * 修改邮箱
	 * @param member id 邮箱
	 */
	void updateEmail(Member member);
	
	/**
	 * 修改用户昵称
	 * @param member 用户id 昵称
	 */
	void updateNickName(Member member);
	
	/**
	 * 会员登录
	 * @param m 手机号 密码
	 * @return
	 */
	Member login(Member m);
	
}
