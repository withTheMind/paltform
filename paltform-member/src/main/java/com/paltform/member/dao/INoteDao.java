package com.paltform.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.platform.entity.Note;
import com.platform.util.page.QueryCondition;

@Mapper
public interface INoteDao {
	
	/**
	 * 分页查询  全部
	 * @param condition 开始条数:默认1,每页显示条数:默认10
	 * @return
	 */
	List<Note> queryList(QueryCondition condition);
	
	/**
	 * 查询总条数
	 * @param condition
	 * @return
	 */
	int tatal(QueryCondition condition);
	
}
