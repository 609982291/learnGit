package com.ssdut.house.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.ssdut.house.dao.FinOrDelayDao;
import com.ssdut.house.dao.YwslZhurenDao;
import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.PageBean;
import com.ssdut.house.entities.UserInfo;
import com.ssdut.house.tools.PublicMethod;
import com.ssdut.house.tools.createUUIDUtils;

public class FinOrDelayAction extends ActionSupport implements RequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> requestMap;
	private String id;
	private int page;
	private String flag;//分页跳转的flag标记为
	private InputStream inputStream;

	private FinOrDelayDao dao=new FinOrDelayDao();
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
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
		PageBean<Case> pageBean=dao.getPageBean(page, (new createUUIDUtils()).size5);
		//System.out.println("pageBean.getList()---->"+pageBean.getList().size());
		requestMap.put("finOrDelaypageBean", pageBean);
		
		return "list";
	}
	public String applyFinish(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		System.out.println("handle id----->"+id);
		Case tem=dao.getModelById(id);
		
		tem.setCaseState("申请结案");
		dao.applyFinishOrDelay(tem);
		return "applyFinish";
	}
	public String applyDelay(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		System.out.println("handle id----->"+id);
		Case tem=dao.getModelById(id);
		String eft=tem.getEFD();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			Date date = sdf.parse(eft);
			
			Date now=new Date();
			
			int days=PublicMethod.daysBetween(now, date);
			System.out.println("days---->"+days);
			if(days==2){
				try {
					tem.setCaseState("申请延期");
					dao.applyFinishOrDelay(tem);
					inputStream = new ByteArrayInputStream(("1").getBytes("UTF-8"));//能正常延期
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					try {
						inputStream = new ByteArrayInputStream(("-1").getBytes("UTF-8"));
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}else{
				try {
					inputStream = new ByteArrayInputStream(("0").getBytes("UTF-8"));//不在规定的期限，不能延期
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		
		return "ajax-success";
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		requestMap=arg0;
	}

}
