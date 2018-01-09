package com.paltform.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paltform.member.service.NoteService;
import com.platform.entity.Member;
import com.platform.entity.Note;
import com.platform.util.page.PageModel;

@Controller
@Scope("prototype")
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@ResponseBody
	@RequestMapping("/queryPage")
	public PageModel<Note> queryPage(HttpServletRequest request) {
		// 当前页
		int pageNo = 1;
		String pageno = request.getParameter("pageNo");
		if (pageno != null && !"".equals(pageno)) {
			pageNo = Integer.parseInt(pageno);
		}
		// session会员信息
		Member member = (Member) request.getSession().getAttribute("member");

		return noteService.queryPage(pageNo, 10, member);
	}

	/**
	 * 随记列表
	 * 
	 * @return
	 */
	@RequestMapping("/noteList")
	public String noteList() {
		return "member/note-list";
	}

}
