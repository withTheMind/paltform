package com.paltform.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.paltform.member.service.MemberService;
import com.platform.entity.Member;
import com.platform.util.ajax.Result;
import com.platform.util.regex.RegexUtil;

/**
 * 账户相关
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/mem")
public class MemberAccountController {
	
	private static Logger logger = LoggerFactory.getLogger(MemberAccountController.class);
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 登录验证
	 * @param member 用户名 密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loginValide")
	public Result loginValide(HttpServletRequest request, Member member){
		
		Result result = new Result();
		if(member == null){
			logger.info("表单提交错误");
			result.setMessage("表单提交错误");
			return result;
		}
		
		//手机号
		if(member.getPhone() == null || "".equals(member.getPhone())){
			logger.info("手机号为空");
			result.setMessage("手机号不能为空");
			return result;
		}
		
		//手机号格式
		if(!RegexUtil.mobile(member.getPhone())){
			logger.info("手机号格式错误:" + member.getPhone());
			result.setMessage("手机号格式错误");
			return result;
		}
		//密码
		if(member.getPassword() == null || "".equals(member.getPassword())){
			logger.info("密码为空");
			result.setMessage("密码不能为空");
			return result;
		}
		
		//登录正确与否
		Member m = memberService.login(member);
		//登录失败
		if(m == null){
			logger.info("用户名或密码错误 :" + JSON.toJSONString(member));
			result.setMessage("用户名或密码错误");
			return result;
		}
		
		//成功
		result.setStatus(200);
		result.setMessage("登录成功");
		//将会员信息存入session
		request.getSession().setAttribute("member", m);
		
		logger.info("登录成功:" + m.getPhone());
		return result;
	}
	
	
	/**
	 * 登录操作
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Member member){
		return "redirect:/mem/loginPage";
	}
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/loginPage")
	public String loginPage(){
		return "login";
	}
	
}
