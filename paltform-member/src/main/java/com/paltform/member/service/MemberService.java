package com.paltform.member.service;

import com.platform.entity.Member;

public interface MemberService {
	
	/**
	 * 会员登录
	 * @param m 手机号 密码
	 * @return
	 */
	Member login(Member m);
	
}
