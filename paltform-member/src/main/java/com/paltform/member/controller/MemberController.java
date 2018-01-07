package com.paltform.member.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("/member")
public class MemberController {
	
	
	/**
	 * 个人中心
	 * @return
	 */
	@RequestMapping("/centerPage")
	public String centerPage(){
		return "member/center-main";
	}
	
}
