package com.ssdut.house.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.SeekableByteChannel;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.ssdut.house.dao.UserDao;
import com.ssdut.house.entities.UserInfo;
import com.ssdut.house.tools.createUUIDUtils;

public class LoginAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loginName;
	private String loginPwd;
	private UserDao dao=new UserDao();
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	public String login(){
		String result="";
		System.out.println("loginName="+loginName+",loginPwd="+loginPwd);
		result=dao.loginCheck(loginName, loginPwd);
		if(!createUUIDUtils.LOGINSUCCESS.equals(result)){
			try {
				inputStream = new ByteArrayInputStream((result).getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				try {
					inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
		}
		else{
			try {
				
				UserInfo user=dao.getUser(loginName, loginPwd);
				session.put("loginUser", user);
				inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "ajax-success";
	}
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}
			
}
