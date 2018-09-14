package com.infotech.sgsy.tcSetup.tcSetupDue;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class TcSetupDueActionForm extends ActionForm {

	 private String id;
	
	 private String projectID;
	 private String proj;
		   
     
	private String trainingCenterId;
     private String recieptDate;
     private String srlmPersonId ;
     private String visitDate;
     private String appRejDate;
     private String statusDueDil;
     private String remarksDueDili;
     private Integer tcAppCapacity;
     private Integer tcAppResidentCapacity;
     private String tcStatus;
     private String remarks;
     
     private String createdOnDate;
 	 private String updatedOnDate;
 	 private String createdBy;
	 private String updatedBy;
	
	 
	 
	 
	 
	 public Integer getTcAppResidentCapacity() {
		return tcAppResidentCapacity;
	}
	public void setTcAppResidentCapacity(Integer tcAppResidentCapacity) {
		this.tcAppResidentCapacity = tcAppResidentCapacity;
	}
	public String getProj() {
			return proj;
		}
		public void setProj(String proj) {
			this.proj = proj;
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
	
 	
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getTrainingCenterId() {
		return trainingCenterId;
	}
	public void setTrainingCenterId(String trainingCenterId) {
		this.trainingCenterId = trainingCenterId;
	}
	public String getRecieptDate() {
		return recieptDate;
	}
	public void setRecieptDate(String recieptDate) {
		this.recieptDate = recieptDate;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getAppRejDate() {
		return appRejDate;
	}
	public void setAppRejDate(String appRejDate) {
		this.appRejDate = appRejDate;
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
	public String getCreatedOnDate() {
		return createdOnDate;
	}
	public void setCreatedOnDate(String createdOnDate) {
		this.createdOnDate = createdOnDate;
	}
	public String getUpdatedOnDate() {
		return updatedOnDate;
	}
	public void setUpdatedOnDate(String updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}
	public String getSrlmPersonId() {
		return srlmPersonId;
	}
	public void setSrlmPersonId(String srlmPersonId) {
		this.srlmPersonId = srlmPersonId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
