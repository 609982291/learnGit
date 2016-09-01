package com.ssdut.house.dao;

import java.util.List;









import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.Department;
import com.ssdut.house.entities.PageBean;

public class PageBeanDao<T> extends BaseDao {
	
	private PageBean<T> pageBean=new PageBean<T>();
	public List<T> getAll(String sql,Class<T> cls){
		utils.getConnection();
		
		List<T> list=null ;
		try {
			list=utils.findMoreRefResult(sql, null,cls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}
		return list;
	}
	public List<T> queryByPage(int page,int pageSize,Class<T> cls){
		utils.getConnection();
		String sql="select * from department";
		List<T> list=getAll(sql,cls);
		pageBean.setCurrentPage(page);
		int allRows=getAllRowCount(sql,cls);
		int totalPage=pageBean.getTotalPages(pageSize, allRows);
		int currentPage=(page);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPages(totalPage);
		pageBean.setList(list);
		list=pageBean.getSubList(pageSize,page);
		return list;
	}
	
	public PageBean<T> getPageBean(String sql,int page,int pageSize,Class<T> cls){
		PageBean<T> pageBean=new PageBean<>();
		utils.getConnection();
		
		int allRows=getAllRowCount(sql,cls);
		int totalPage=pageBean.getTotalPages(pageSize, allRows);
		int currentPage=page;
		
		List<T> list=getAll(sql,cls);
		
		System.out.println("listAll-->>"+list);
		System.out.println("page="+currentPage+"pageSize"+pageSize+"totalPage"+totalPage);
		if(list!=null&&list.size()>0){
			list=getSubList(list, totalPage, pageSize, currentPage);
			pageBean.setList(list);
			pageBean.setAllRows(allRows);
			pageBean.setCurrentPage(currentPage);
			pageBean.setTotalPages(totalPage);
		}
		
		return pageBean;
	}
	public List<T> getSubList(List<T> list,int totalPages,int pageSize,int currentPage){
		//currentPage=currentPage-1;
		if(currentPage!=totalPages-1){
			return list.subList(currentPage*pageSize,(currentPage+1)*pageSize);//ע��βҳ
		}
		else if(currentPage==totalPages-1){
			//System.out.println(data.size());
			return list.subList(currentPage*pageSize,list.size());
		}
		return null;
		
	}
	private int getAllRowCount(String sql,Class<T> cls) {
		// TODO Auto-generated method stub
		
		return getAll(sql,cls).size();
	}
	public void delete(Integer id){
	
	}
}
