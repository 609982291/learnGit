package com.ssdut.house.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssdut.house.entities.UserInfo;
import com.ssdut.house.tools.PublicMethod;

import freemarker.template.utility.Execute;

public class UploadAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	private String fileContentType;
	private String fileFileName;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Thread t1=new Thread(new UploadThread());
		t1.start();
		t1.join();
		return super.execute();
	}
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	public UserInfo checkLogin(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String,Object> session = actionContext.getSession();
		
		UserInfo user=(UserInfo) session.get("loginUser");
		return user;
	}
	public void uploadFile(){
		
		System.out.println(file);
		System.out.println(fileContentType);
		System.out.println(fileFileName);
		ServletContext servletContext=ServletActionContext.getServletContext();
		String dir=servletContext.getRealPath("/files/upload/"+fileFileName);
		
		System.out.println(dir);
		try{
			FileOutputStream out=new FileOutputStream(dir);
			BufferedOutputStream bout=new BufferedOutputStream(out);
			
			FileInputStream in=new FileInputStream(file);
			BufferedInputStream bin=new BufferedInputStream(in);
			byte [] buffer=new byte[1024*8];
			int len=0;
			while((len=bin.read(buffer))!=-1){
				bout.write(buffer, 0, len);
			}
			bout.close();bin.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public String upload(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		uploadFile();
		try {
			inputStream = new ByteArrayInputStream("<script>alert('upload success');window.location.href='under-list.jsp';</script>".getBytes("UTF-8"));
			
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
		
		return "ajax-success";
	}
	class UploadThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			
		}
		
	}
}
