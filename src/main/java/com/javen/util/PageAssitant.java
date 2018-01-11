package com.javen.util;

import java.util.ArrayList;
import java.util.List;

public class PageAssitant<T> {
	/** 总记录数 */
	private Integer count = 0;
	/** 总页数 */
	private Integer totalPage = 0;
	/** 页面大小 */
	private Integer pageSize = 9;
	/** 当前页码 */
	private Integer currPage = 1;
	/** 当前页起始记录编号 */
	private Integer currStart = 0;
	/** 页码页数 */
	private Integer pageNum = 1;
	
	/** 当前页的数据集合 */
	List<T> items = new ArrayList<T>();
	
	public PageAssitant() {
	}

	/** 获取总记录数 */
	public Integer getCount() {
		return count;
	}
	/** 设置总记录数,同时设置总页数 */
	public void setCount(Integer count) {
		this.count = count;
		setTotalPage();//总页数
	}
	
	/** 获取总页数 */
	public Integer getTotalPage() {
		return totalPage;
	}
	
	/** 设置总页数
	 * 由 总记录数 / 页面大小 来计算 */
	private void setTotalPage() {
		totalPage = count / pageSize;
		if(count % pageSize != 0){
			totalPage++;
		}
	}
	
	/** 获取页面大小 */
	public Integer getPageSize() {
		return pageSize;
	}
	/** 设置页面大小<br>
	 * 同时设置总页数 和 当前页的起始记录数 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		setTotalPage();//总页数
		setCurrStart();//当前页的起始数
	}
	
	/** 获取当前页码 */
	public Integer getCurrPage() {
		return currPage;
	}
	/** 设置当前页码<br>
	 * 同时设置当前页码的起始数 */
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
		setCurrStart();//当前页的起始数
	}
	
	/** 获取当前页的起始数 */
	public Integer getCurrStart() {
		return currStart;
	}
	/** 设置当前页的起始数
	 * 当前页-1 * 页面大小  + 1 */
	private void setCurrStart() {
		currStart = (currPage - 1) * pageSize;
	}

	/** 获取当前页的数据集合 */
	public List<T> getItems(){
		return items;
	}
	/** 设置当前页的数据集合 */
	public void setItems(List<T> items) {
		this.items = items;
	}
}
