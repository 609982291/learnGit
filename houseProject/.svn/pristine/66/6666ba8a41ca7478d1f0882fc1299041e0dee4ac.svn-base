package com.ssdut.house.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.PageBean;

public class YwslZhurenDao extends BaseDao {
	private PageBeanDao<Case> pageBeanDao=new PageBeanDao<Case>();
	public PageBean<Case> getPageBean(int page,int pageSize){
		String sql="select * from allcase where caseState='已受理'";
		return pageBeanDao.getPageBean(sql, page, pageSize,Case.class);
	}
	/**
	 * 根据id获取案件对象
	 * @param id
	 * @return
	 */
	public Case getModelById(String id){
		utils.getConnection();
		String sql="select * from allcase where id=?";
		Case c = null;
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		try{
			c=(Case) utils.findSimpleRefResult(sql, params, Case.class);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}	
		return c;
	}
	/**
	 * 主任受理
	 * @param c
	 */
	public void  handleZhuren(Case c){
		utils.getConnection();
		String sql="update allcase set acceptDepId=?,acceptDepOp=? ,caseState=? where id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(c.getAcceptDepId());
		params.add(c.getAcceptDepOp());
	
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
