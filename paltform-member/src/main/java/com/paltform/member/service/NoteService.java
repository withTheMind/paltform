package com.paltform.member.service;

import com.platform.entity.Member;
import com.platform.entity.Note;
import com.platform.util.page.PageModel;

public interface NoteService {
	
	/**
	 * 分页查询
	 * @param currentPage 当前页
	 * @param size 条数
	 * @param member 会员id
	 * @return
	 */
	PageModel<Note> queryPage(int currentPage, int size, Member member);
	
}
