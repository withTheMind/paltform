package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 随记
 */
public class Note implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int noteId;//
	private String content;//随记内容
	private Date createTime;//创建时间
	private Member member;//创建人
	
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
}
