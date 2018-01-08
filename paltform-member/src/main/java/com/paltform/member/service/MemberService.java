package com.paltform.member.service;

import com.platform.entity.Member;

public interface MemberService {
	
	
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
