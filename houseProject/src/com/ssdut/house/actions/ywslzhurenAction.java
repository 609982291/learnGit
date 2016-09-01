package com.ssdut.house.actions;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.ssdut.house.dao.YwslZhurenDao;
import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.PageBean;
import com.ssdut.house.entities.UserInfo;
import com.ssdut.house.tools.PublicMethod;
import com.ssdut.house.tools.createUUIDUtils;

public class ywslzhurenAction extends ActionSupport implements RequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private YwslZhurenDao caseDao=new YwslZhurenDao();
	private Map<String, Object> requestMap;
	private String id;
	private int page;
	private String flag;//分页跳转的flag标记为
	private String acceptDepId;
	private String acceptDepOp;
	
	public void setFlag(String flag) {
		this.flag = flag;}
	public void setAcceptDepId(String acceptDepId) {
		this.acceptDepId = acceptDepId;
	}
	public void setAcceptDepOp(String acceptDepOp) {
		this.acceptDepOp = acceptDepOp;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		requestMap=arg0;
	}
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
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
		System.out.println("page----"+page);
		PageBean<Case> pageBean=caseDao.getPageBean(page, (new createUUIDUtils()).size5);
		//System.out.println("zhurenpageBean.getList()---->"+pageBean.getList().size());
		requestMap.put("zhurenpageBean", pageBean);
		
		return "list";
	}
	public String handle(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		System.out.println("handle id----->"+id);
		Case tem=caseDao.getModelById(id);
		tem.setAcceptDepId(user.getDepId());
		tem.setAcceptDepOp(acceptDepOp);
		
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=format.format(new Date());
	
		tem.setCaseState("待审核");
		caseDao.handleZhuren(tem);
		return "handle";
	}
}
