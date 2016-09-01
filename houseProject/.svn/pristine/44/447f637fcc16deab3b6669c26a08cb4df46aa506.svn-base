package com.ssdut.house.actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ssdut.house.dao.DepartmentDao;
import com.ssdut.house.dao.YwdbDao;
import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.Department;
import com.ssdut.house.entities.PageBean;
import com.ssdut.house.entities.UserInfo;
import com.ssdut.house.tools.PublicMethod;
import com.ssdut.house.tools.createUUIDUtils;

public class YwdbAction extends ActionSupport implements RequestAware,
		ModelDriven<Case>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String auditOp;// 督查室审核意见
	private String auditAcceptDate;//监察室受理时间、或者法制科受理时间
	private String undertakeDepId;// 承办部门id
	private String EFD;// 预计完成时间
	private String id;
	private int page;
	private String flag;// 分页跳转的flag标记为
	private Map<String, Object> requestMap;
	private YwdbDao dao = new YwdbDao();
	private DepartmentDao departmentDao = new DepartmentDao();
	public void setAuditAcceptDate(String auditAcceptDate) {
		this.auditAcceptDate = auditAcceptDate;
	}
	public void setUndertakeDepId(String undertakeDepId) {
		this.undertakeDepId = undertakeDepId;
	}

	public void setAuditOp(String auditOp) {
		this.auditOp = auditOp;
	}

	public void setEFD(String eFD) {
		EFD = eFD;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		requestMap = arg0;
	}

	public String list() {
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		if ("go".equals(flag)) {
			// 分页
			--page;
			System.out.println("page----" + page + "flag--->" + flag);
		}
		System.out.println("page----" + page);
		PageBean<Case> pageBean = dao.getPageBean(page,
				(new createUUIDUtils()).size5);
		// System.out.println("ywdbpadgeBean.getList()---->"+pageBean.getList().size());
		requestMap.put("ywdbpageBean", pageBean);

		return "list";
	}
	public void prepareTohandle() {
		System.out.println("prepareInput id="+id);
		if(id!=null&&!"".equals(id)){
			model = dao.getModelById(id);
		}
	}
	public String tohandle() {
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		List<Department> depts = departmentDao.getAllDepartments();
		requestMap.put("departments", depts);
		return "tohandle";
	}

	public void prepareHandle() {

		if (id == null || "".equals(id)) {
			System.out.println("prepareSave model==null");
			model = new Case();
		} else {
			System.out.println("prepareSave model!=null  id=" + id);
			model = dao.getModelById(id);
		}
	}

	public String handle() {
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		System.out.println("handle id----->" + id);
		Case tem = dao.getModelById(id);
		tem.setAuditOp(auditOp);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = format.format(new Date());
		tem.setAuditAcceptDate(auditAcceptDate);
		tem.setEFD(EFD);
		tem.setUndertakeDepId(undertakeDepId);
		tem.setCaseState("审核通过");
		dao.handle(tem);
		return "handle";
	}

	public String reject() {
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		Case tem = dao.getModelById(id);
		tem.setAuditOp(auditOp);
		tem.setCaseState("已回绝");
		dao.reject(tem);
		return "reject";
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
