package com.paltform.member.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paltform.member.dao.IMemberDao;
import com.paltform.member.service.MemberService;
import com.platform.entity.Member;
import com.platform.util.md5.MessageUtil;

@Service
public class MemberServiceImpl implements MemberService{
	
	private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private IMemberDao memberDao;
	
	
	@Override
	public Member login(Member m) {
		//密码加密
		m.setPassword(MessageUtil.encodeMD5(m.getPassword()));
		try {
			//登录
			return memberDao.login(m);
		} catch (Exception e) {
			logger.error("登录异常:" + e.getMessage(), e);
		}
		
		return null;
	}
	
}
