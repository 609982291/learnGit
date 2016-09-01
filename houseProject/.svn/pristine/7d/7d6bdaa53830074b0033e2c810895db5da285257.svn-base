package com.ssdut.house.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.PageBean;

public class UnderDao extends BaseDao{
	private PageBeanDao<Case> pageBeanDao=new PageBeanDao<Case>();
	public PageBean<Case> getPageBean(int page,int pageSize){
		String sql="select * from allcase where caseState='处理中'";
		return pageBeanDao.getPageBean(sql, page, pageSize,Case.class);
	}
	public void notake(Case c) {
		// TODO Auto-generated method stub
		utils.getConnection();
		String sql="update allcase set caseState=?,subState=? where id=?";
		List<Object> params=new ArrayList<Object>();
		
		params.add(c.getCaseState());
		params.add(c.getSubState());
		params.add(c.getId());
		
		try{
			utils.updateByPreparedStatement(sql, params);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}	
	}
	public void handle(Case c) {
		// TODO Auto-generated method stub
		utils.getConnection();
		String sql="update allcase set interviewRecord=?,caseRemark=?,caseState=?,hasFile=? where id=?";
		List<Object> params=new ArrayList<Object>();
		
		params.add(c.getInterviewRecord());
		params.add(c.getCaseRemark());
		params.add(c.getCaseState());
		params.add(c.getHasFile());
		params.add(c.getId());
		try{
			utils.updateByPreparedStatement(sql, params);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}	
	}
	
}
