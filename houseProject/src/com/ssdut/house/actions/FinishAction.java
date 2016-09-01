package com.ssdut.house.actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.ssdut.house.dao.FinishDao;
import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.PageBean;
import com.ssdut.house.entities.UserInfo;
import com.ssdut.house.tools.PublicMethod;
import com.ssdut.house.tools.createUUIDUtils;

public class FinishAction extends ActionSupport implements RequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> requestMap;
	private String id;
	private int page;
	private String flag;//分页跳转的flag标记为
	private String leaderOp;//督查室领导意见,最终结案意见
	private String finishDate;//完成时间
	private FinishDao dao=new FinishDao();
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

	public String getLeaderOp() {
		return leaderOp;
	}

	public void setLeaderOp(String leaderOp) {
		this.leaderOp = leaderOp;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
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
		//System.out.println("zhurenpageBean.getList()---->"+pageBean.getList().size());
		requestMap.put("finishPageBean", pageBean);
		
		return "list";
	}
	public String finish(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		System.out.println("handle id----->"+id);
		Case tem=dao.getModelById(id);
		
		tem.setLeaderOp(leaderOp);
		
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=format.format(new Date());
		tem.setFinishDate(finishDate);
		tem.setCaseState("已结案");
		dao.finish(tem);
		return "finish";
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		requestMap=arg0;
	}

}
