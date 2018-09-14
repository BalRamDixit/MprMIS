package com.infotech.sgsy.projectSanctionDetail;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.util.DateUtil;

@SuppressWarnings("serial")
public class SanctionDetailVO implements Serializable {

	private String id;
	private ProjectDetailsVO projectId;
	 private String projectName;
	               //...new added...13.april..
	private Date pcoDate;
		               	//...ends here...	  
	private String sanOrder;
	private Integer residential;
	private Integer nonresidential;
	private Integer sanTarget;
	private Date sanctionDate;
	private Integer projectDuration;
	private Date commDate;
	private Date endDate;
	private Date trainingcompletion;	
	private Date placementcompletion;
	
	
	private String mouSigned;
	private Date mouSignedDate;
	private String pwsApproved;
	private Date pwsDate;
	private String perApproved;
	private Date perDate;
	private String projectStatus;
	private String remark;
	
	
	private String sc_st;
	private String general;
	private String minority;
	private String women;
	
	private String createdBy;
	private String updatedBy;
	private Date createdOn;
	private Date updatedOn;
	
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
	public Date getPcoDate() {
		return pcoDate;
	}
	public void setPcoDate(Object pcoDate) {
		if(pcoDate instanceof String){
			this.pcoDate = new DateUtil().Correct(pcoDate.toString());
		}
		else{
			this.pcoDate =(Date) pcoDate;
		}
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

	public String getSc_st() {
		return sc_st;
	}
	public void setSc_st(String sc_st) {
		this.sc_st = sc_st;
	}
	public String getGeneral() {
		return general;
	}
	public void setGeneral(String general) {
		this.general = general;
	}
	public String getMinority() {
		return minority;
	}
	public void setMinority(String minority) {
		this.minority = minority;
	}
	public String getWomen() {
		return women;
	}
	public void setWomen(String women) {
		this.women = women;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ProjectDetailsVO getProjectId() {
		return projectId;
	}
	public void setProjectId(ProjectDetailsVO projectId) {
		this.projectId = projectId;
	}
	public String getSanOrder() {
		return sanOrder;
	}
	public void setSanOrder(String sanOrder) {
		this.sanOrder = sanOrder;
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
	public String getMouSigned() {
		return mouSigned;
	}
	public void setMouSigned(String mouSigned) {
		this.mouSigned = mouSigned;
	}
	
	public String getPwsApproved() {
		return pwsApproved;
	}
	public void setPwsApproved(String pwsApproved) {
		this.pwsApproved = pwsApproved;
	}
	
	public String getPerApproved() {
		return perApproved;
	}
	public void setPerApproved(String perApproved) {
		this.perApproved = perApproved;
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
	public Date getSanctionDate() {
		return sanctionDate;
	}
	
	public void setSanctionDate(Object sanctionDate) {
		if(sanctionDate instanceof String){
			this.sanctionDate = new DateUtil().Correct(sanctionDate.toString());
		}
		else{
			this.sanctionDate =(Date) sanctionDate;
		}
		
	}
	public Date getCommDate() {
		return commDate;
	}
	
	public void setCommDate(Object commDate) {
		if(commDate instanceof String){
			this.commDate = new DateUtil().Correct(commDate.toString());
		}
		else{
			this.commDate =(Date) commDate;
		}
		
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Object endDate) {
		if(endDate instanceof String){
			this.endDate = new DateUtil().Correct(endDate.toString());
		}
		else{
			this.endDate =(Date) endDate;
		}
	}
	
	
	
	
	
	public Date getTrainingcompletion() {
		return trainingcompletion;
	}
	public void setTrainingcompletion(Object trainingcompletion) {
		if(trainingcompletion instanceof String){
			this.trainingcompletion = new DateUtil().Correct(trainingcompletion.toString());
		}
		else{
			this.trainingcompletion =(Date) trainingcompletion;
		}
	}
	public Date getPlacementcompletion() {
		return placementcompletion;
	}
	public void setPlacementcompletion(Object placementcompletion) {
		if(placementcompletion instanceof String){
			this.placementcompletion = new DateUtil().Correct(placementcompletion.toString());
		}
		else{
			this.placementcompletion =(Date) placementcompletion;
		}
	}
	public Date getMouSignedDate() {
		return mouSignedDate;
	}
	public void setMouSignedDate(Object mouSignedDate) {
		if(mouSignedDate instanceof String){
			this.mouSignedDate = new DateUtil().Correct(mouSignedDate.toString());
		}
		else{
			this.mouSignedDate =(Date) mouSignedDate;
		}
	}
	public Date getPwsDate() {
		return pwsDate;
	}
	public void setPwsDate(Object pwsDate) {
		if(pwsDate instanceof String){
			this.pwsDate = new DateUtil().Correct(pwsDate.toString());
		}
		else{
			this.pwsDate =(Date) pwsDate;
		}
	}
	public Date getPerDate() {
		return perDate;
	}
	public void setPerDate(Object perDate) {
		if(perDate instanceof String){
			this.perDate = new DateUtil().Correct(perDate.toString());
		}
		else{
			this.perDate =(Date) perDate;
		}
	}
	
}
