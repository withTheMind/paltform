package com.paltform.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.paltform.member.Application;
import com.paltform.member.dao.IMemberDao;
import com.platform.entity.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MemberTest {
	
	@Autowired
	private IMemberDao memberDao;
	
	@Test
	public void test(){
		
		Member m = new Member();
		m.setPhone("15082037343");
		m.setPassword("123456");
		
		
		try {
			Member member = memberDao.login(m);
			
			System.out.println(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
