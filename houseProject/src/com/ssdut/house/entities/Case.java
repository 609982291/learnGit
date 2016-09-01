package com.ssdut.house.entities;

public class Case {
	private String id;
	private String caseTypeId;//投诉形式id
	private String caseUserName;//投诉人姓名
	private String caseUserPhone;//投诉人联系方式
	private String companyName;//被投诉公司名称
	private String companyPhone;//被投诉公司联系方式
	private String companyLegalPerson;//被投诉公司法人
	private String companyType;//被投诉公司类型
	private String companyProject;//被投诉公司项目
	private String caseContent;//案件描述
	private String complaintDate;//投诉时间
	private String acceptDate;//受理时间
	private String acceptUserId;//受理人id
	private String acceptDepId;//受理部门id
	private String acceptUserOp;//受理人意见
	private String acceptDepOp;//受理部门意见
	private String auditOp;//督查室审核意见
	private String auditAcceptDate;//监察室受理时间、或者法制科受理时间
	private String caseState;//案件处理状态
	private String caseRemark;//备注
	private String researchResult;//调查情况
	private String dealOp;//处理意见
	private String interviewRecord;//访谈笔录
	private String undertakeUserId;//承办人id
	private String undertakeDepId;//承办部门id
	private String undertakeUserOp;//承办人意见
	private String undertakeDepOp;//承办部门意见
	private String leaderOp;//督查室领导意见,最终结案意见
	private String typicalCase;//是否是典型案例（是|否）
	private String EFD;//预计完成时间
	private String finishDate;//完成时间
	private String hasFile;//是否有附件
	private String subState;//无法处理时的状态描述
	
	public String getSubState() {
		return subState;
	}
	public void setSubState(String subState) {
		this.subState = subState;
	}
	public String getHasFile() {
		return hasFile;
	}
	public void setHasFile(String hasFile) {
		this.hasFile = hasFile;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCaseTypeId() {
		return caseTypeId;
	}
	public void setCaseTypeId(String caseTypeId) {
		this.caseTypeId = caseTypeId;
	}
	public String getCaseUserName() {
		return caseUserName;
	}
	public void setCaseUserName(String caseUserName) {
		this.caseUserName = caseUserName;
	}
	public String getCaseUserPhone() {
		return caseUserPhone;
	}
	public void setCaseUserPhone(String caseUserPhone) {
		this.caseUserPhone = caseUserPhone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyLegalPerson() {
		return companyLegalPerson;
	}
	public void setCompanyLegalPerson(String companyLegalPerson) {
		this.companyLegalPerson = companyLegalPerson;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getCompanyProject() {
		return companyProject;
	}
	public void setCompanyProject(String companyProject) {
		this.companyProject = companyProject;
	}
	public String getCaseContent() {
		return caseContent;
	}
	public void setCaseContent(String caseContent) {
		this.caseContent = caseContent;
	}
	public String getComplaintDate() {
		return complaintDate;
	}
	public void setComplaintDate(String complaintDate) {
		this.complaintDate = complaintDate;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getAcceptUserId() {
		return acceptUserId;
	}
	public void setAcceptUserId(String acceptUserId) {
		this.acceptUserId = acceptUserId;
	}
	public String getAcceptDepId() {
		return acceptDepId;
	}
	public void setAcceptDepId(String acceptDepId) {
		this.acceptDepId = acceptDepId;
	}
	public String getAcceptUserOp() {
		return acceptUserOp;
	}
	public void setAcceptUserOp(String acceptUserOp) {
		this.acceptUserOp = acceptUserOp;
	}
	public String getAcceptDepOp() {
		return acceptDepOp;
	}
	public void setAcceptDepOp(String acceptDepOp) {
		this.acceptDepOp = acceptDepOp;
	}
	public String getAuditOp() {
		return auditOp;
	}
	
	public String getAuditAcceptDate() {
		return auditAcceptDate;
	}
	public void setAuditAcceptDate(String auditAcceptDate) {
		this.auditAcceptDate = auditAcceptDate;
	}
	public void setAuditOp(String auditOp) {
		this.auditOp = auditOp;
	}
	public String getCaseState() {
		return caseState;
	}
	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}
	public String getCaseRemark() {
		return caseRemark;
	}
	public void setCaseRemark(String caseRemark) {
		this.caseRemark = caseRemark;
	}
	public String getResearchResult() {
		return researchResult;
	}
	public void setResearchResult(String researchResult) {
		this.researchResult = researchResult;
	}
	public String getDealOp() {
		return dealOp;
	}
	public void setDealOp(String dealOp) {
		this.dealOp = dealOp;
	}
	public String getInterviewRecord() {
		return interviewRecord;
	}
	public void setInterviewRecord(String interviewRecord) {
		this.interviewRecord = interviewRecord;
	}
	public String getUndertakeUserId() {
		return undertakeUserId;
	}
	public void setUndertakeUserId(String undertakeUserId) {
		this.undertakeUserId = undertakeUserId;
	}
	public String getUndertakeDepId() {
		return undertakeDepId;
	}
	public void setUndertakeDepId(String undertakeDepId) {
		this.undertakeDepId = undertakeDepId;
	}
	public String getUndertakeUserOp() {
		return undertakeUserOp;
	}
	public void setUndertakeUserOp(String undertakeUserOp) {
		this.undertakeUserOp = undertakeUserOp;
	}
	public String getUndertakeDepOp() {
		return undertakeDepOp;
	}
	public void setUndertakeDepOp(String undertakeDepOp) {
		this.undertakeDepOp = undertakeDepOp;
	}
	public String getLeaderOp() {
		return leaderOp;
	}
	public void setLeaderOp(String leaderOp) {
		this.leaderOp = leaderOp;
	}
	public String getTypicalCase() {
		return typicalCase;
	}
	public void setTypicalCase(String typicalCase) {
		this.typicalCase = typicalCase;
	}
	public String getEFD() {
		return EFD;
	}
	public void setEFD(String eFD) {
		EFD = eFD;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
}
