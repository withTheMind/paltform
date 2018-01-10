package com.paltform.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.platform.entity.Member;

@Mapper
public interface IMemberDao {
	
	/**
	 * 修改会员密码
	 * @param member 账号 密码
	 */
	void updatePassword(Member member);
	
	/**
	 * 注册
	 * @param member 账号 密码 注册时间
	 */
	void register(Member member);
	
	/**
	 * 根据手机号查询
	 * @param phone 手机号
	 * @return
	 */
	Member queryByPhone(String phone);
	
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
	Member login(Member member);
	
	
}
