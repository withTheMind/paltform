package com.paltform.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.paltform.member.service.MemberService;
import com.platform.entity.Member;
import com.platform.entity.logger.EmailLogger;
import com.platform.entity.logger.NickNameLogger;
import com.platform.util.ajax.Result;
import com.platform.util.md5.MessageUtil;
import com.platform.util.regex.RegexUtil;

@Controller
@Scope("prototype")
@RequestMapping("/member")
public class MemberController {

	private static Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	/**
	 * 修改密码
	 * 
	 * @param oldPwd
	 *            原密码
	 * @param pwd
	 *            新密码
	 * @param surePwd
	 *            确认密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updatePwd")
	public Result updatePwd(HttpServletRequest request, String oldPwd, String pwd, String surePwd) {
		Result result = new Result();

		// 原密非空
		if (oldPwd == null || "".equals(oldPwd)) {
			logger.info("原密码为空");
			result.setMessage("原密码不能为空");
			return result;
		}
		// 原密码长度
		if (oldPwd.length() < 6 || oldPwd.length() > 20) {
			logger.info("原密码长度错误:" + oldPwd);
			result.setMessage("原密码长度应在6-20位之间");
			return result;
		}

		// 新密非空
		if (pwd == null || "".equals(pwd)) {
			logger.info("新密码为空");
			result.setMessage("新密码不能为空");
			return result;
		}
		// 新密码长度
		if (pwd.length() < 6 || pwd.length() > 20) {
			logger.info("新密码长度错误:" + pwd);
			result.setMessage("新密码长度应在6-20位之间");
			return result;
		}
		if(pwd.equals(oldPwd)){
			logger.info("新密码不能和原密码一样");
			result.setMessage("新密码不能和原密码一样");
			return result;
		}
		if(!pwd.equals(surePwd)){
			logger.info("新密码不能和原密码一样");
			result.setMessage("新密码和确认密码不一致");
			return result;
		}
		
		//session会员信息
		Member member = (Member)request.getSession().getAttribute("member");
		
		//原密码是否正确
		if(!memberService.judgePassword(member, oldPwd)){
			logger.info("原密码错误");
			result.setMessage("原密码错误");
			return result;
		}
		
		
		member.setPassword(MessageUtil.encodeMD5(pwd));
		//TODO 修改密码
		memberService.updatePassword(member);
		
		result.setStatus(200);//成功
		result.setMessage("密码修改成功,请重新登录");
		request.getSession().invalidate();
		return result;
	}

	/**
	 * 修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping("/updatePwdPage")
	public String updatePwdPage() {
		return "/member/update-password";
	}

	/**
	 * 修改邮箱
	 * 
	 * @param email
	 *            邮箱
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateEmail")
	public Result updateEmail(HttpServletRequest request, String email) {
		Result result = new Result();

		// 邮箱为空
		if (email == null || "".equals(email)) {
			logger.info("邮箱为空");
			result.setMessage("邮箱不能为空");
			return result;
		}

		// 邮箱格式
		if (!RegexUtil.email(email)) {
			logger.info("邮箱格式错误" + email);
			result.setMessage("邮箱格式不正确");
			return result;
		}

		// session会员
		Member member = (Member) request.getSession().getAttribute("member");

		EmailLogger emailLogger = new EmailLogger();
		emailLogger.setPhone(member.getPhone());// 账户
		emailLogger.setOldEmail(member.getEmail());// 旧邮箱
		emailLogger.setNewEmail(email);// 新邮箱

		logger.info("邮箱修改成功:" + emailLogger);

		// 修改session会员邮箱
		member.setEmail(email);

		// 修改邮箱
		memberService.updateEmail(member);

		result.setStatus(200);
		result.setMessage(email);// 新邮箱
		return result;
	}

	/**
	 * 昵称验证
	 * 
	 * @param nickName
	 *            昵称
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changeNickName")
	public Result changeNickName(HttpServletRequest request, String nickName) {
		Result result = new Result();
		// 昵称判断
		if (nickName == null || "".equals(nickName) || nickName.length() < 2 || nickName.length() > 10) {
			logger.info("昵称验证失败:" + nickName);
			result.setMessage("昵称长度在2-10个字符之间");
			return result;
		}

		// session 会员信息
		Member member = (Member) request.getSession().getAttribute("member");

		// 日志记录实体
		NickNameLogger nameLogger = new NickNameLogger();
		nameLogger.setPhone(member.getPhone());// 手机号
		nameLogger.setOldNickName(member.getNickName());// 原来昵称
		nameLogger.setNewNickName(nickName);// 新昵称
		logger.info("昵称修改:" + nameLogger);

		member.setNickName(nickName);// 昵称
		// 修改会员昵称
		memberService.updateNickName(member);

		result.setStatus(200);// 验证通过
		result.setMessage(nickName);// 提示信息
		return result;
	}

	/**
	 * 获取session会员信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMember")
	public Member getMember(HttpServletRequest requset) {
		Member member = (Member) requset.getSession().getAttribute("member");
		return member;
	}

	/**
	 * 更换头像
	 * 
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updatePhoto")
	public Result updatePhoto(@RequestParam("upload") MultipartFile file) {

		System.out.println(file);
		return null;
	}

	/**
	 * 设置
	 * 
	 * @return
	 */
	@RequestMapping("/setting")
	public String setting() {
		return "member/member-setting";
	}

	/**
	 * 个人中心
	 * 
	 * @return
	 */
	@RequestMapping("/centerPage")
	public String centerPage() {
		return "member/center-main";
	}

}
