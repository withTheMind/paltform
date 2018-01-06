package com.paltform.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paltform.member.service.MemberService;
import com.platform.entity.Member;

@Controller
@Scope("prototype")
public class TestController {
	
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@RequestMapping("/test")
	public Map<String, Object> test(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test", "hello world");
		
		Member m = new Member();
		m.setPhone("15082037343");
		m.setPassword("123456");
		
		map.put("memberService", memberService.login(m));
		System.out.println(memberService);
		return map;
	}
	
}
