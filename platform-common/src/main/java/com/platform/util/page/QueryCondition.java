package com.platform.util.page;

/**
 * 分页查询条件
 */
public class QueryCondition {

	private int start;// 开始条数
	private int size;// 显示条数
	private Object obj;// 其他条件（对象属性）

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
