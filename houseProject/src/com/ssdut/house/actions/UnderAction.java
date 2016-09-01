package com.ssdut.house.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ssdut.house.dao.AttachmentDao;
import com.ssdut.house.dao.UnderDao;
import com.ssdut.house.entities.Attachment;
import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.PageBean;
import com.ssdut.house.entities.UserInfo;
import com.ssdut.house.tools.PublicMethod;
import com.ssdut.house.tools.createUUIDUtils;

public class UnderAction extends ActionSupport implements RequestAware,
		ModelDriven<Case>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String interviewRecord;// 访谈笔录
	private String caseRemark;// 备注
	private String id;
	private int page;
	private String flag;// 分页跳转的flag标记为
	private File file;
	private String fileContentType;
	private String fileFileName;
	private List<Object> allfiles;
	
	public List<Object> getAllfiles() {
		return allfiles;
	}

	public void setAllfiles(List<Object> allfiles) {
		this.allfiles = allfiles;
	}

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

	private UnderDao dao = new UnderDao();
	private AttachmentDao attachmentDao=new AttachmentDao();
	public String getInterviewRecord() {
		return interviewRecord;
	}

	public void setInterviewRecord(String interviewRecord) {
		this.interviewRecord = interviewRecord;
	}

	public String getCaseRemark() {
		return caseRemark;
	}

	public void setCaseRemark(String caseRemark) {
		this.caseRemark = caseRemark;
	}

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

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		requestMap = arg0;
	}

	private Map<String, Object> requestMap;

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
		requestMap.put("underpageBean", pageBean);
		return "list";
	}

	public String notake() {
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		Case tem = dao.getModelById(id);

		tem.setCaseState("待审核");
		tem.setSubState("无法处理");
		dao.notake(tem);
		return "notake";
	}
/**
 * 外出授权
 * @return
 */
	public String authorize() {
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		Case tem = dao.getModelById(id);

		tem.setCaseState("审核通过");
		tem.setSubState("外出授权");
		dao.notake(tem);
		return "notake";
	}
	public void prepareTohandle() {
		System.out.println("prepareInput id=" + id);
		if (id != null && !"".equals(id)) {
			model = dao.getModelById(id);
		}
	}
	public String tohandle(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		List<Attachment> list=attachmentDao.getAttachmentsById(id);
		requestMap.put("files",list);
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
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	public String upload(){
		uploadFile();
		Attachment a=new Attachment();
		a.setId((new createUUIDUtils()).uuid);
		System.out.println("file id---->"+a.getId());
		a.setAttachmentOrgName(fileFileName);
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String now = format.format(new Date());
		a.setAttachmentReName(now+fileFileName);
		a.setCaseId(id);
		attachmentDao.addModel(a);
		try {
			inputStream = new ByteArrayInputStream(("<script>alert('upload success');window.location.href='under-tohandle?id="+id+"';</script>").getBytes("UTF-8"));
			
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
	public String handle() {
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		System.out.println("handle id----->" + id);
		
		Case tem = dao.getModelById(id);
		tem.setInterviewRecord(interviewRecord);
		tem.setCaseRemark(caseRemark);
		tem.setCaseState("约谈笔录完成");
		List<Attachment> list=attachmentDao.getAttachmentsById(id);
		if(list==null||list.size()==0){
			tem.setHasFile("false");
		}else{
			tem.setHasFile("true");
			attachmentDao.setAttachmentsEffective(id,"有效");
			
		}
		
		
		dao.handle(tem);
		return "handle";
	}
	public String deleteFile(){
		UserInfo user=PublicMethod.checkLogin();
		if(user==null){
			return "nologin";
		}
		if(allfiles==null||allfiles.size()==0){
			
		}
		else{
			System.out.println(allfiles);
			for(int i=0;i<allfiles.size();i++){
				String id=(String)allfiles.get(i);
				attachmentDao.delete(id);
			}
			try {
				inputStream = new ByteArrayInputStream(("<script>alert('upload success');window.location.href='under-tohandle?id="+id+"';</script>").getBytes("UTF-8"));
				
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
		return "deleteFile";
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
