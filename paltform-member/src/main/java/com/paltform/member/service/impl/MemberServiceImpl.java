package com.paltform.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paltform.member.dao.IMemberDao;
import com.paltform.member.service.MemberService;
import com.platform.entity.Member;
import com.platform.util.md5.MessageUtil;

@Service
public class MemberServiceImpl implements MemberService{
	
	
	@Autowired
	private IMemberDao memberDao;
	
	
	@Override
	public Member login(Member m) {
		
		System.out.println(memberDao);
		//密码加密
		m.setPassword(MessageUtil.encodeMD5(m.getPassword()));
		Member member = memberDao.login(m);
		
		return member;
	}
	
}
