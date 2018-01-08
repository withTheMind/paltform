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
import com.platform.entity.logger.NickNameLogger;
import com.platform.util.ajax.Result;

@Controller
@Scope("prototype")
@RequestMapping("/member")
public class MemberController {
	
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 昵称验证
	 * @param nickName 昵称
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changeNickName")
	public Result changeNickName(HttpServletRequest request, String nickName){
		Result result = new Result();
		//昵称判断
		if(nickName == null || "".equals(nickName) || nickName.length() < 2 || nickName.length() > 10){
			logger.info("昵称验证失败:" + nickName);
			result.setMessage("昵称长度在2-10个字符之间");
			return result;
		}
		
		//session 会员信息
		Member member = (Member)request.getSession().getAttribute("member");
		
		//日志记录实体
		NickNameLogger nameLogger = new NickNameLogger();
		nameLogger.setPhone(member.getPhone());//手机号
		nameLogger.setOldNickName(member.getNickName());//原来昵称
		nameLogger.setNewNickName(nickName);//新昵称
		logger.info("昵称修改:" + nameLogger);
		
		member.setNickName(nickName);//昵称
		//修改会员昵称
		memberService.updateNickName(member);
		
		result.setStatus(200);//验证通过
		result.setMessage(nickName);//提示信息
		return result;
	}
	
	/**
	 * 获取session会员信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMember")
	public Member getMember(HttpServletRequest requset){
		Member member = (Member)requset.getSession().getAttribute("member");
		return member;
	}
	
	/**
	 * 更换头像
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updatePhoto")
	public Result updatePhoto(@RequestParam("upload") MultipartFile file){
		
		System.out.println(file);
		return null;
	}
	
	/**
	 * 设置
	 * @return
	 */
	@RequestMapping("/setting")
	public String setting(){
		return "member/member-setting";
	}
	
	/**
	 * 个人中心
	 * @return
	 */
	@RequestMapping("/centerPage")
	public String centerPage(){
		return "member/center-main";
	}
	
}
