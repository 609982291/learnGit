package com.ssdut.house.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ssdut.house.dao.AjfpDao;
import com.ssdut.house.dao.UserDao;
import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.PageBean;
import com.ssdut.house.entities.UserInfo;
import com.ssdut.house.tools.PublicMethod;
import com.ssdut.house.tools.createUUIDUtils;

public class AjfpAction extends ActionSupport implements RequestAware,ModelDriven<Case>, Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;    
	private String undertakeUserId;//承办人id
	private Map<String, Object> requestMap;
	private String id;
	private int page;
	private String flag;//分页跳转的flag标记为
	private AjfpDao dao=new AjfpDao();
	private UserDao userDao=new UserDao();
	public void setId(String id) {
		this.id = id;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setUndertakeUserId(String undertakeUserId) {
		this.undertakeUserId = undertakeUserId;
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		requestMap=arg0;
	}
	public String list(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		if("go".equals(flag)){
			//分页
			--page;
			System.out.println("page----"+page+"flag--->"+flag);
		}
		PageBean<Case> pageBean=dao.getPageBean(page, (new createUUIDUtils()).size5);
		requestMap.put("ajfppageBean", pageBean);
		List<UserInfo> list=userDao.getAll();
		requestMap.put("ajfpusers", list);
		return "list";
	}
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	public void prepareTodispatch() {
		System.out.println("prepareInput id="+id);
		if(id!=null&&!"".equals(id)){
			model = dao.getModelById(id);
		}
	}
	public String todispatch(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		System.out.println("todispatch id--->"+id);
		Case tem=dao.getModelById(id);
		String dept=tem.getUndertakeDepId();
		List<UserInfo> list=userDao.getDeptAll(dept);
		requestMap.put("ajfpusers", list);
		
		return "todispatch";
	}
	public void prepareDispatch() {

		if (id == null || "".equals(id)) {
			System.out.println("prepareSave model==null");
			model = new Case();
		} else {
			System.out.println("prepareSave model!=null  id=" + id);
			model = dao.getModelById(id);
		}
	}
	public String dispatch(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		Case tem=dao.getModelById(id);
		tem.setUndertakeUserId(undertakeUserId);
		tem.setCaseState("处理中");
		dao.dispatch(tem);
		return "dispatch";
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	private Case model;
	@Override
	public Case getModel() {
		// TODO Auto-generated method stub
		return model;
	}
}
