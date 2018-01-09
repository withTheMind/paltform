package com.platform.util.page;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 前台分页工具类
 */
public class PageModel<T> {
	
	private int currentPage = 1;//当前页
	private int size = 10;//条数
	private int total;//总条数
	private int sumPage;//总页数
	private List<T> list;//当前页数据
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
		//设置总页数
		this.sumPage = total%this.size == 0 ? total/this.size : total/this.size + 1;
	}
	public int getSumPage() {
		return sumPage;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
