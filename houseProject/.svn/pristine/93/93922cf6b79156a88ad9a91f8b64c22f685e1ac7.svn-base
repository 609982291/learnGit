package com.ssdut.house.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssdut.house.entities.Attachment;
import com.ssdut.house.entities.Case;


public class AttachmentDao extends BaseDao {
	public List<Attachment> getAttachmentsById(String caseId){
		utils.getConnection();
		String sql="select * from attachment where caseId=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(caseId);//通过params为占位符赋值
		List<Attachment> list=null ;
			try {
				list=utils.findMoreRefResult(sql, params, Attachment.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				utils.releaseConn();
			}			
		return list;
	}
    
	public boolean addModel(Attachment c) {
		utils.getConnection();
		String sql = "insert into attachment"
				+ "(id,attachmentOrgName,attachmentReName,caseId)"
				+ " values(?,?,?,?)";

		List<Object> params = new ArrayList<Object>();
		params.add(c.getId());
		params.add(c.getAttachmentOrgName());
		params.add(c.getAttachmentReName());
		params.add(c.getCaseId());

		try {
			return utils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			utils.releaseConn();
		}
		return false;
	}
	public void delete(String id){
		utils.getConnection();
		String sql="delete from attachment where id=?";
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

	public void setAttachmentsEffective(String caseId,String state) {
		// TODO Auto-generated method stub
		utils.getConnection();
		String sql="update attachment set state=? where caseId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(state);
		params.add(caseId);
		
		try{
			utils.updateByPreparedStatement(sql, params);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}	
	}
	
}
