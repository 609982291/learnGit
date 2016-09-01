package com.ssdut.house.dao;
import java.util.ArrayList;
import java.util.List;

import com.ssdut.house.entities.*;
public class CaseDao extends BaseDao {
	/**
	 * 获取所有的案件
	 * @return List
	 */
	private PageBeanDao<Case> pageBeanDao=new PageBeanDao<Case>();
	public List<Case> getAllCases(){
		utils.getConnection();
		String sql="select * from allcase";
		List<Case> list=null ;
			try {
				list=utils.findMoreRefResult(sql, null, Case.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				utils.releaseConn();
			}			
		return list;
	}
	public PageBean<Case> getPageBean(int page,int pageSize){
		String sql="select * from allcase";
		return pageBeanDao.getPageBean(sql, page, pageSize,Case.class);
	}
	/**
	 * 根据案件的处理状态查询对应的案件
	 * @param state
	 * @return
	 */
	public List<Case> getCasesByState(String state){
		utils.getConnection();
		String sql="select * from allcase where caseState=?";
		List<Object> params = new ArrayList<Object>();
		params.add(state);//通过params为占位符赋值
		List<Case> list=null ;
			try {
				list=utils.findMoreRefResult(sql, params, Case.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				utils.releaseConn();
			}			
		return list;
	}
    
	
   /**
    * 新增
    * @param c
    * @return
    */
	public boolean addModel(Case c){
		utils.getConnection();
			String sql="insert into allcase"
					+ "(id,caseTypeId,caseUserName,caseUserPhone,complaintDate,"
					+ "companyName,companyPhone,caseContent,caseState)"
					+ " values(?,?,?,?,?,?,?,?,?)";
		
			List<Object> params=new ArrayList<Object>();
			params.add(c.getId());
			params.add(c.getCaseTypeId());params.add(c.getCaseUserName());
			params.add(c.getCaseUserPhone());params.add(c.getComplaintDate());
			params.add(c.getCompanyName());params.add(c.getCompanyPhone());
			params.add(c.getCaseContent());	
			params.add(c.getCaseState());
			try{
				return utils.updateByPreparedStatement(sql, params);
			}catch(Exception e){	
				e.printStackTrace();
			}finally{
				utils.releaseConn();
			}
			return false;
	}
	
	
	/**
	    * 更新
	    * @param c
	    * @return
	    */
		public boolean updateModel(Case c){
			utils.getConnection();
			String sql="update allcase set caseTypeId=?,"
					+ "caseUserName=?,caseUserPhone=?,"
					+ "complaintDate=?,companyName=?,companyPhone=?,caseContent=?"
					+ " where id=?";
				List<Object> params=new ArrayList<Object>();
				
				params.add(c.getCaseTypeId());params.add(c.getCaseUserName());
				params.add(c.getCaseUserPhone());params.add(c.getComplaintDate());
				params.add(c.getCompanyName());params.add(c.getCompanyPhone());
				params.add(c.getCaseContent());	
				
				params.add(c.getId());
				try{
					return utils.updateByPreparedStatement(sql, params);
				}catch(Exception e){	
					e.printStackTrace();
				}finally{
					utils.releaseConn();
				}
				return false;
		}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id){
		utils.getConnection();
		String sql="delete from allcase where id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		try{
			utils.updateByPreparedStatement(sql, params);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}
	}
	/**
	 * 受理
	 * @param c
	 */
	public void  handleXfdj(Case c){
		utils.getConnection();
		String sql="update allcase set acceptUserId=?,acceptUserOp=? ,acceptDate=?,caseState=? where id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(c.getAcceptUserId());
		params.add(c.getAcceptUserOp());
		params.add(c.getAcceptDate());
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
