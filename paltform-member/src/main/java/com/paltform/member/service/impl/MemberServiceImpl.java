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
	public void updatePassword(Member member) {
		try {
			memberDao.updatePassword(member);
		} catch (Exception e) {
			logger.info("密码修改失败:" + e.getMessage(), e);
		}
	}
	
	@Override
	public boolean judgePassword(Member member, String pwd) {
		
		try {
			//数据库查询
			Member mem = memberDao.queryByPhone(member.getPhone());
			
			return MessageUtil.encodeMD5(pwd).equals(mem.getPassword()) ? true : false;
			
		} catch (NullPointerException e) {
			logger.error("judgePassword:" + e.getMessage(), e);
		}
		
		return false;
	}
	
	@Override
	public void register(Member member) {
		
		//加密
		member.setPassword(MessageUtil.encodeMD5(member.getPassword()));
		try {
			memberDao.register(member);
		} catch (Exception e) {
			logger.error("注册失败:" + e.getMessage(), e);
		}
		
	}
	
	@Override
	public boolean queryByPhone(String phone) {
		return memberDao.queryByPhone(phone) == null ? false : true;
	}
	
	@Override
	public void updateEmail(Member member) {
		try {
			memberDao.updateEmail(member);
		} catch (Exception e) {
			logger.error("邮箱修改失败:" + member.getEmail(), e);
		}
	}
	
	@Override
	public void updateNickName(Member member) {
		try {
			memberDao.updateNickName(member);
		} catch (Exception e) {
			logger.error("昵称修改失败:" + member.getNickName(), e);
		}
		
	}
	
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
