package com.infotech.sgsy.projectSanctionDetail;

import java.util.Date;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class SanctionDetailsForm extends ActionForm{
	
	private String id;
	private String projectId;
     private String projectName;
	//...new added...13.april..
	private String pcoDate;
		//...ends here...	  
	private String stateName;
	
	private String sanOrder;
	private Integer residential;
	private Integer nonresidential;
	private Integer sanTarget;
	
	
	private String sanctionDate;
	private Integer projectDuration;
	private String commDate;
	private String endDate;
	private String trainingcompletion;	
	private String placementcompletion;
	private String mouSigned;
	private String mouSignedDate;
	private String pwsApproved;
	
	
	private String pwsDate;
	private String perApproved;
	private String perDate;
	private String projectStatus;
	private String remark;
	
	private String createdBy;
	private String updatedBy;
	private Date createdOn;
	private Date updatedOn;
	
   private String dateOfapproval;
	 private String registerToPfms;
	 private String agencyCode;
	 private String accountNumber;
	 private String bankName;
	 private String ifscCode;
	 
	 
	 
	
	public Integer getResidential() {
		return residential;
	}
	public void setResidential(Integer residential) {
		this.residential = residential;
	}
	public Integer getNonresidential() {
		return nonresidential;
	}
	public void setNonresidential(Integer nonresidential) {
		this.nonresidential = nonresidential;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getRegisterToPfms() {
		return registerToPfms;
	}
	public void setRegisterToPfms(String registerToPfms) {
		this.registerToPfms = registerToPfms;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getStateName() {
	return stateName;
}
public String getPcoDate() {
		return pcoDate;
	}
	public void setPcoDate(String pcoDate) {
		this.pcoDate = pcoDate;
	}
public void setStateName(String stateName) {
	this.stateName = stateName;
}
	public String getDateOfapproval() {
		return dateOfapproval;
	}
	public void setDateOfapproval(String dateOfapproval) {
		this.dateOfapproval = dateOfapproval;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getSanOrder() {
		return sanOrder;
	}
	public void setSanOrder(String sanOrder) {
		this.sanOrder = sanOrder;
	}
	
	public String getSanctionDate() {
		return sanctionDate;
	}
	public void setSanctionDate(String sanctionDate) {
		this.sanctionDate = sanctionDate;
	}
	
	public Integer getSanTarget() {
		return sanTarget;
	}
	public void setSanTarget(Integer sanTarget) {
		this.sanTarget = sanTarget;
	}
	public Integer getProjectDuration() {
		return projectDuration;
	}
	public void setProjectDuration(Integer projectDuration) {
		this.projectDuration = projectDuration;
	}
	public String getCommDate() {
		return commDate;
	}
	public void setCommDate(String commDate) {
		this.commDate = commDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMouSigned() {
		return mouSigned;
	}
	public void setMouSigned(String mouSigned) {
		this.mouSigned = mouSigned;
	}
	public String getMouSignedDate() {
		return mouSignedDate;
	}
	public void setMouSignedDate(String mouSignedDate) {
		this.mouSignedDate = mouSignedDate;
	}
	public String getPwsApproved() {
		return pwsApproved;
	}
	public void setPwsApproved(String pwsApproved) {
		this.pwsApproved = pwsApproved;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getPwsDate() {
		return pwsDate;
	}
	public void setPwsDate(String pwsDate) {
		this.pwsDate = pwsDate;
	}
	public String getPerApproved() {
		return perApproved;
	}
	public void setPerApproved(String perApproved) {
		this.perApproved = perApproved;
	}
	public String getPerDate() {
		return perDate;
	}
	public void setPerDate(String perDate) {
		this.perDate = perDate;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTrainingcompletion() {
		return trainingcompletion;
	}
	public void setTrainingcompletion(String trainingcompletion) {
		this.trainingcompletion = trainingcompletion;
	}
	public String getPlacementcompletion() {
		return placementcompletion;
	}
	public void setPlacementcompletion(String placementcompletion) {
		this.placementcompletion = placementcompletion;
	}
	

}
