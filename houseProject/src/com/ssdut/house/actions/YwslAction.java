package com.ssdut.house.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.catalina.User;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssdut.house.dao.CaseDao;
import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.PageBean;
import com.ssdut.house.entities.UserInfo;
import com.ssdut.house.tools.PublicMethod;
import com.ssdut.house.tools.createUUIDUtils;

public class YwslAction extends  ActionSupport implements RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CaseDao caseDao=new CaseDao();
	private Map<String, Object> requestMap;
	private String id;
	private int page;
	private String acceptUserId;
	private String acceptUserOp;
	private String acceptDate;//受理时间
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	private String flag;//分页跳转的flag标记为
	public void setFlag(String flag) {
		this.flag = flag;}
	public void setAcceptUserId(String acceptUserId) {
		this.acceptUserId = acceptUserId;
	}
	public void setAcceptUserOp(String acceptUserOp) {
		this.acceptUserOp = acceptUserOp;
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
	public String delete(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		try {
			caseDao.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
		
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
		//System.out.println("pageBean.getList()---->"+pageBean.getList().size());
		requestMap.put("pageBean", pageBean);
		
		return "list";
	}
	public String handle(){
		
		
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		System.out.println(user);
		System.out.println("handle id----->"+id+",acceptDate---->"+acceptDate+",acceptUserOp----->"+acceptUserOp);
		Case tem=caseDao.getModelById(id);
		tem.setAcceptUserId(user.getId());
		tem.setAcceptUserOp(acceptUserOp);
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=format.format(new Date());
		tem.setAcceptDate(acceptDate);
		tem.setCaseState("已受理");
		caseDao.handleXfdj(tem);
		return "handle";
	}

}
