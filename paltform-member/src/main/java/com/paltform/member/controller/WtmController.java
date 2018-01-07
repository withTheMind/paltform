package com.paltform.member.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class WtmController {
	
	/**
	 * 首页 nav
	 * @return
	 */
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/main")
	public String main(){
		return "main";
	}
	
	/**
	 * 个人中心
	 * @return
	 */
	@RequestMapping("/center")
	public String center(){
		return "center";
	}
	
	/**
	 * 未登录
	 * @return
	 */
	@RequestMapping("/notLogin")
	public String notLogin(){
		return "center-not-login-main";
	}
	
}
