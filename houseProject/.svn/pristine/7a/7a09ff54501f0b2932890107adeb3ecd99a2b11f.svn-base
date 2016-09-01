package com.ssdut.house.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.Department;
import com.ssdut.house.entities.PageBean;

public class DepartmentDao extends BaseDao{
	private PageBeanDao<Department> pageBeanDao=new PageBeanDao<Department>();
	public List<Department> getAllDepartments(){
		utils.getConnection();
		String sql="select * from department";
		List<Department> list=null ;
			try {
				list=utils.findMoreRefResult(sql, null, Department.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				utils.releaseConn();
			}
			
		return list;
	}
	public PageBean<Department> getPageBean(int page,int pageSize){
		String sql="select * from department";
		return pageBeanDao.getPageBean(sql, page, pageSize,Department.class);
	}
	public void delete(String id) {
		// TODO Auto-generated method stub
		utils.getConnection();
		String sql="delete from department where id=?";
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

	public Department getByID(String id) {
		// TODO Auto-generated method stub
		utils.getConnection();
		String sql="select * from department where id=?";
		Department dept=null;
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		
		try{
			dept=(Department) utils.findSimpleRefResult(sql, params, Department.class);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}	
		return dept;
	}
	public void add(Department dept){
		String sql="insert into department(deptName) values(?)";
		List<Object> params=new ArrayList<Object>();
		params.add(dept.getDeptName());
		try{
			utils.updateByPreparedStatement(sql, params);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}
	}
	public void update(Department dept){
		String sql="update  department set deptName=? where id= ?";
		List<Object> params=new ArrayList<Object>();
		params.add(dept.getDeptName());
		params.add(dept.getId());
		try{
			utils.updateByPreparedStatement(sql, params);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}
	}
}
