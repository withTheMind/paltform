package com.paltform.member.controller;

import java.util.Date;

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
import com.paltform.member.util.YzmEntity;
import com.platform.entity.Member;
import com.platform.util.RandomUtil;
import com.platform.util.ajax.Result;
import com.platform.util.regex.RegexUtil;

/**
 * 账户相关
 * 
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
	 * 设置登录密码
	 * @param pwd 登录密码
	 * @param surePwd 确认密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loginPwd")
	public Result loginPwd(HttpServletRequest request, String pwd, String surePwd){
		Result result = new Result();
		
		if(pwd == null || "".equals(pwd)){
			result.setMessage("密码不能为空");
			logger.info("密码不能为空");
			return result;
		}
		
		if(pwd.length() < 6 || pwd.length() > 20){
			result.setMessage("密码长度应在6-20位之间");
			logger.info("密码长度错误:" + pwd);
			return result;
		}
		
		if(!pwd.equals(surePwd)){
			result.setMessage("两次密码不一致");
			logger.info("两次密码不一致,pwd:" + pwd + ",surePwd:" + surePwd);
			return result;
		}
		
		//session验证码信息
		YzmEntity yzmEntity = (YzmEntity)request.getSession().getAttribute("yzm-info");
		
		if(yzmEntity == null){
			result.setMessage("请先获取验证码");
			logger.info("未获取验证码");
			return result;
		}
		
		Member member = new Member();
		member.setPhone(yzmEntity.getPhone());//账号
		member.setRegisterTime(new Date());//注册时间
		member.setPassword(pwd);//密码
		
		//注册
		memberService.register(member);
		logger.info("注册成功:" + member.getPhone());
		//session移除
		request.getSession().removeAttribute("yzm-info");
		result.setStatus(200);
		return result;
	}
	
	/**
	 * 设置密码页面
	 * @return
	 */
	@RequestMapping("/settingPwd")
	public String settingPwd(){
		return "setting-password";
	}

	/**
	 * 注册
	 * 
	 * @param phone
	 *            手机号
	 * @param yzm
	 *            验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/register")
	public Result register(HttpServletRequest request, String phone, String yzm) {
		Result result = new Result();

		// 手机号非空
		if (phone == null || "".equals(phone)) {
			result.setMessage("手机号不能空");
			logger.info("手机号不能为空");
			return result;
		}

		// 手机号格式
		if (!RegexUtil.mobile(phone)) {
			result.setMessage("手机号格式错误");
			logger.info("手机号格式错误:" + phone);
			return result;
		}

		// 手机号是否注册
		if (memberService.queryByPhone(phone)) {
			logger.info("手机号已被注册:" + phone);
			result.setMessage("手机号已被注册");
			return result;
		}

		// 验证码非空
		if (yzm == null || "".equals(yzm)) {
			result.setMessage("验证码不能为空");
			logger.info("验证码不能为空");
			return result;
		}

		
		YzmEntity yzmEntity = (YzmEntity)request.getSession().getAttribute("yzm-info");
		if(yzmEntity == null){
			result.setMessage("请先获取验证码");
			logger.info("未获取验证码");
			return result;
		}
		
		//手机号不正确
		if(!phone.equals(yzmEntity.getPhone())){
			result.setMessage("该手机号与接收验证码手机号不符合");
			logger.info("手机号不符,接收验证码手机号:" + yzmEntity.getPhone() + ",注册手机号:" + phone);
			return result;
		}
		
		//验证码过期 有效期30min
		if((System.currentTimeMillis() - yzmEntity.getSendTime().getTime()) > 30*60*1000){
			result.setMessage("验证码过期，请重新获取");
			logger.info("验证码过期");
			return result;
		}
		
		//验证码是否正确
		if(!yzm.equals(yzmEntity.getYzm())){
			result.setMessage("验证码错误");
			logger.info("验证码错误,手机验证码:" + yzmEntity.getYzm() + ",注册验证码:" + yzm);
			return result;
		}
		
		result.setStatus(200);// 通过
		return result;
	}

	/**
	 * 发送验证码
	 * 
	 * @param request
	 * @param phone
	 *            手机号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendYzm")
	public Result sendYzm(HttpServletRequest request, String phone) {
		Result result = new Result();

		// 手机号不能为空
		if (phone == null || "".equals(phone)) {
			logger.info("手机号为空");
			result.setMessage("手机号不能为空");
			return result;
		}

		// 手机号格式
		if (!RegexUtil.mobile(phone)) {
			logger.info("手机号格式错误:" + phone);
			result.setMessage("手机号格式错误");
			return result;
		}

		// 手机号是否注册
		if (memberService.queryByPhone(phone)) {
			logger.info("手机号已被注册:" + phone);
			result.setMessage("手机号已被注册");
			return result;
		}

		// 生成6位数验证码
		String yzm = RandomUtil.random();

		String content = "你的验证码为:" + yzm + ",验证码有效时间30min,请妥善保管。";

		YzmEntity yzmEntity = new YzmEntity();
		yzmEntity.setPhone(phone);// 手机号
		yzmEntity.setYzm(yzm);// 验证码
		yzmEntity.setSendTime(new Date());// 发送时间

		// 存入session
		request.getSession().setAttribute("yzm-info", yzmEntity);

		System.out.println(content);
		// TODO 发送验证码
		// ShortMessageUtil.sendMessage(smsMob, smsText)

		result.setStatus(200);// 成功
		result.setMessage("验证码发送成功");// 请注意查收
		logger.info("验证码发送成功:" + yzmEntity);

		return result;
	}

	/**
	 * 注册页面
	 * 
	 * @return
	 */
	@RequestMapping("/registerPage")
	public String registerPage() {
		return "register";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/member/centerPage";
	}

	/**
	 * 登录验证
	 * 
	 * @param member
	 *            用户名 密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loginValide")
	public Result loginValide(HttpServletRequest request, Member member) {

		Result result = new Result();
		if (member == null) {
			logger.info("表单提交错误");
			result.setMessage("表单提交错误");
			return result;
		}

		// 手机号
		if (member.getPhone() == null || "".equals(member.getPhone())) {
			logger.info("手机号为空");
			result.setMessage("手机号不能为空");
			return result;
		}

		// 手机号格式
		if (!RegexUtil.mobile(member.getPhone())) {
			logger.info("手机号格式错误:" + member.getPhone());
			result.setMessage("手机号格式错误");
			return result;
		}
		// 密码
		if (member.getPassword() == null || "".equals(member.getPassword())) {
			logger.info("密码为空");
			result.setMessage("密码不能为空");
			return result;
		}

		// 登录正确与否
		Member m = memberService.login(member);
		// 登录失败
		if (m == null) {
			logger.info("用户名或密码错误 :" + JSON.toJSONString(member));
			result.setMessage("用户名或密码错误");
			return result;
		}

		// 成功
		result.setStatus(200);
		result.setMessage("登录成功");
		// 将会员信息存入session
		request.getSession().setAttribute("member", m);

		logger.info("登录成功:" + m.getPhone());
		return result;
	}

	/**
	 * 登录操作
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Member member) {
		return "redirect:/center";
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/loginPage")
	public String loginPage() {
		return "login";
	}

}
