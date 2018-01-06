package com.paltform.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.platform.entity.Member;

@Mapper
public interface IMemberDao {
	
	/**
	 * 会员登录
	 * @param m 手机号 密码
	 * @return
	 */
	Member login(Member member);
	
}
