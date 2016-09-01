package com.ssdut.house.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.PageBean;

public class YwdbDao extends BaseDao{
	private PageBeanDao<Case> pageBeanDao=new PageBeanDao<Case>();
	public PageBean<Case> getPageBean(int page,int pageSize){
		String sql="select * from allcase where caseState='待审核'";
		return pageBeanDao.getPageBean(sql, page, pageSize,Case.class);
	}
	public void handle(Case c) {
		// TODO Auto-generated method stub
		
		utils.getConnection();
		String sql="update allcase set auditOp=?,auditAcceptDate=? ,EFD=?,undertakeDepId=? ,caseState=? where id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(c.getAuditOp());
		params.add(c.getAuditAcceptDate());
		params.add(c.getEFD());
		params.add(c.getUndertakeDepId());
		params.add(c.getCaseState());
		params.add(c.getId());
		
		try{
			utils.updateByPreparedStatement(sql, params);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}	
	}
	public void reject(Case c) {
		// TODO Auto-generated method stub
		utils.getConnection();
		String sql="update allcase set auditOp=?,caseState=? where id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(c.getAuditOp());
		params.add(c.getCaseState());
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
