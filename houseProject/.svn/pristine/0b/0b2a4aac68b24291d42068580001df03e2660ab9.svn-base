package com.ssdut.house.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.PageBean;

public class FinishDao extends BaseDao{
	private PageBeanDao<Case> pageBeanDao=new PageBeanDao<Case>();
	public PageBean<Case> getPageBean(int page,int pageSize){
		String sql="select * from allcase where caseState='申请结案'";
		return pageBeanDao.getPageBean(sql, page, pageSize,Case.class);
	}
	
	/**
	 * 结案
	 * @param c
	 */
	public void  finish(Case c){
		utils.getConnection();
		String sql="update allcase set finishDate=?,leaderOp=? ,caseState=? where id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(c.getFinishDate());
		params.add(c.getLeaderOp());
	
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
