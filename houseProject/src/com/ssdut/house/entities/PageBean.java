package com.ssdut.house.entities;
import java.util.*;
public class PageBean<T> {
	private List<T> list;
	private int allRows;
	private int totalPage;
	private int currentPage;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getAllRows() {
		return allRows;
	}

	public void setAllRows(int allRows) {
		this.allRows = allRows;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPages(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 得到总页数
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param allRows
	 *            总记录数
	 * @return 总页数
	 */
	public int getTotalPages(int pageSize, int allRows) {
		int totalPage = (allRows % pageSize == 0) ? (allRows / pageSize)
				: (allRows / pageSize) + 1;

		return totalPage;
	}

	/**
	 * 得到当前开始记录号
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param currentPage
	 *            当前页
	 * @return
	 */
	public int getCurrentPageOffset(int pageSize, int currentPage) {
		int offset = pageSize * (currentPage - 1);

		return offset;
	}

	/**
	 * 得到当前页， 如果为0 则开始第一页，否则为当前页
	 * 
	 * @param page
	 * @return
	 */
//	public int getCurPage(int page) {
//		int currentPage = (page == 0) ? 1 : page;
//
//		return currentPage;
//	}

	public List<T> getSubList(int pageSize,int currentPage){
		int totalPages;
		
		totalPages=getTotalPages(pageSize,currentPage);
		if(currentPage!=totalPages-1){
			return list.subList(currentPage*pageSize,(currentPage+1)*pageSize);//ע��βҳ
		}
		else if(currentPage==totalPages-1){
			//System.out.println(data.size());
			return list.subList(currentPage*pageSize,list.size());
		}
		return null;
		
	}
	
}
