package com.infotech.sgsy.tcSetup.tcSetupDue;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.stateSetup.hrTeam.HRTeamVO;
import com.infotech.sgsy.tcSetup.tcSetupEntry.TcSetupVO;
import com.infotech.sgsy.util.DateUtil;

public class TcSetupDueVO implements Serializable {
	
	 private String id;
	/* private ProjectDetailsVO projectID;*/
	// private String projectID;
	 
     private HRTeamVO srlmPersonId ;
     private TcSetupVO trainingCenterId;
     private Date recieptDate;
     private Date visitDate;
     private Date appRejDate;
     private String statusDueDil;
     private String remarksDueDili;
     private Integer tcAppCapacity;
     private String tcStatus;
     private String remarks;
     private Integer tcAppResidentCapacity; 
     private Date createdOnDate;
 	 private Date updatedOnDate;
 	 private String createdBy;
	 private String updatedBy;
	 
	 
	 
 	 public Integer getTcAppResidentCapacity() {
		return tcAppResidentCapacity;
	}
	public void setTcAppResidentCapacity(Integer tcAppResidentCapacity) {
		this.tcAppResidentCapacity = tcAppResidentCapacity;
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
	
	
	
	/*public ProjectDetailsVO getProjectID() {
		return projectID;
	}
	public void setProjectID(ProjectDetailsVO projectID) {
		this.projectID = projectID;
	}
	public String getTrainingCenterId() {
		return trainingCenterId;
	}
	public void setTrainingCenterId(String trainingCenterId) {
		this.trainingCenterId = trainingCenterId;
	}*/
	
	public Date getRecieptDate() {
		return recieptDate;
	}
	
	public TcSetupVO getTrainingCenterId() {
		return trainingCenterId;
	}
	public void setTrainingCenterId(TcSetupVO trainingCenterId) {
		this.trainingCenterId = trainingCenterId;
	}
	public void setRecieptDate(Object recieptDate) {
		if(recieptDate instanceof String){
			this.recieptDate = new DateUtil().Correct(recieptDate.toString());
		}
		else{
			this.recieptDate =(Date) recieptDate;
		}
		
	}
	
	public Date getVisitDate() {
		return visitDate;
	}
	
	public void setVisitDate(Object visitDate) {
		if(visitDate instanceof String){
			this.visitDate = new DateUtil().Correct(visitDate.toString());
		}
		else{
			this.visitDate =(Date) visitDate;
		}
		
	}
	public Date getAppRejDate() {
		return appRejDate;
	}
	public void setAppRejDate(Object appRejDate) {
		if(appRejDate instanceof String){
			this.appRejDate = new DateUtil().Correct(appRejDate.toString());
		}
		else{
			this.appRejDate =(Date) appRejDate;
		}
		
	}
		
	public String getStatusDueDil() {
		return statusDueDil;
	}
	public void setStatusDueDil(String statusDueDil) {
		this.statusDueDil = statusDueDil;
	}
	public String getRemarksDueDili() {
		return remarksDueDili;
	}
	public void setRemarksDueDili(String remarksDueDili) {
		this.remarksDueDili = remarksDueDili;
	}
	public Integer getTcAppCapacity() {
		return tcAppCapacity;
	}
	public void setTcAppCapacity(Integer tcAppCapacity) {
		this.tcAppCapacity = tcAppCapacity;
	}
	public String getTcStatus() {
		return tcStatus;
	}
	public void setTcStatus(String tcStatus) {
		this.tcStatus = tcStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreatedOnDate() {
		return createdOnDate;
	}
	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}
	public Date getUpdatedOnDate() {
		return updatedOnDate;
	}
	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}
	
	public HRTeamVO getSrlmPersonId() {
		return srlmPersonId;
	}
	public void setSrlmPersonId(HRTeamVO srlmPersonId) {
		this.srlmPersonId = srlmPersonId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
