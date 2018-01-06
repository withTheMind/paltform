package com.paltform.member.dao;

import com.platform.entity.Member;

public interface IMemberDao {
	
	/**
	 * 会员登录
	 * @param m 手机号 密码
	 * @return
	 */
	Member login(String phone, String password);
	
}
