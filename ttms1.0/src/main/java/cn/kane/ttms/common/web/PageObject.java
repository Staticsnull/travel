package cn.kane.ttms.common.web;

import java.io.Serializable;
/*
 * web包用与封装页面对象  该类用于封装具体的分页信息
 */
public class PageObject implements Serializable {
	private static final long serialVersionUID = -8753809986545361268L;
	//当前页
	private int pageCurrent = 1;
	//每页最多显示的记录数
	private int pageSize = 2;
	//总记录数
	private int rowCount;
	//总页数
	private int pageCount;
	//上一页的最后以条记录值 对应
	//limit里的startIndex
	private int startIndex;
	
	@Override
	public String toString() {
		return "PageObject [pageCurrent=" + pageCurrent + ", pageSize=" + pageSize + ", rowCount=" + rowCount
				+ ", pageCount=" + pageCount + ", startIndex=" + startIndex + "]";
	}
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
