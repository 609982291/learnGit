package com.ssdut.house.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ssdut.house.dao.DepartmentDao;
import com.ssdut.house.dao.PageBeanDao;
import com.ssdut.house.entities.Department;
import com.ssdut.house.entities.PageBean;

public class DepartmentAction extends  ActionSupport implements RequestAware, ModelDriven<Department>,
Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int page;
	private String id;
	
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	private DepartmentDao dao=new DepartmentDao() ;
	private PageBeanDao<Department> pageBeanDao=new PageBeanDao<Department>();
	public void setId(String id) {
		this.id = id;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	private Department model;
	@Override
	public Department getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	private Map<String, Object> requestMap;
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		requestMap=arg0;
	}
	public String list(){
		String sql="select * from department";
		PageBean<Department> pageBean=pageBeanDao.getPageBean(sql,page, 5,Department.class);
		requestMap.put("pageBean", pageBean);
		return "list";
	}
	public String delete() {
		try {
			dao.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
				System.out.println("0页没成功");
			}
		}
		return "ajax-success";
	}
	public String input(){
		System.out.println("id------>"+id);
		return INPUT;
	}
	public void prepareInput(){
		if(id != null){
			model = dao.getByID(id);
		}
	}
	public String save(){
		if(id == null){
			dao.add(model);
			
		}else{
			dao.update(model);
		}
		
		return SUCCESS;
	}
	public String tomain(){
		return "index";
	}
}
