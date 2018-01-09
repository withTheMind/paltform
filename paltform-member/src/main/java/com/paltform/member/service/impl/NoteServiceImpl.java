package com.paltform.member.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paltform.member.dao.INoteDao;
import com.paltform.member.service.NoteService;
import com.platform.entity.Member;
import com.platform.entity.Note;
import com.platform.util.page.PageModel;
import com.platform.util.page.QueryCondition;

@Service
public class NoteServiceImpl implements NoteService{
	
	private static Logger logger = LoggerFactory.getLogger(NoteService.class);
	
	@Autowired
	private INoteDao noteDao;

	@Override
	public PageModel<Note> queryPage(int currentPage, int size, Member member) {
		
		QueryCondition condition = new QueryCondition();
		condition.setStart((currentPage - 1) * size);//开始条数
		condition.setSize(size);//条数
		
		Note note = new Note();
		note.setMember(member);//会员id
		condition.setObj(note);
		
		PageModel<Note> pm = new PageModel<Note>();
		pm.setCurrentPage(currentPage);//当前页号
		pm.setSize(size);//每页显示条数
		pm.setTotal(noteDao.tatal(condition));//总条数
		pm.setList(noteDao.queryList(condition));//当前页数据
		
		logger.info("查询结果:" + pm);
		
		return pm;
	}

}
