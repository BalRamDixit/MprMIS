package com.infotech.sgsy.monitoringalerts;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.util.DateUtil;

public class MonitoringAlertVO implements Serializable{
	
	public String id;
	public ProjectDetailsVO projectID;	 
	public String typeOfAlert;	
	public Date dateOfIssue;
	public String reasoncategory;
	public String reasonForIssue;
	public String issuingAgency;	
	public Date dateOfReplyFromPia;	
    public String replyFromPia;
	public String status;
	public Date communicationToPiadate;
	public String remarks;
 	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	 
 
	public String getTypeOfAlert() {
		return typeOfAlert;
	}
	public void setTypeOfAlert(String typeOfAlert) {
		this.typeOfAlert = typeOfAlert;
	}
	public Date getDateOfIssue() {
		return dateOfIssue;
	}	 
	public void setDateOfIssue(Object dateOfIssue) {
 		if(dateOfIssue instanceof String){
			this.dateOfIssue = new DateUtil().Correct(dateOfIssue.toString());
		}
		else{
			this.dateOfIssue =(Date) dateOfIssue;
		}	 
	} 
	public String getReasonForIssue() {
		return reasonForIssue;
	}
	public void setReasonForIssue(String reasonForIssue) {
		this.reasonForIssue = reasonForIssue;
	}
	public String getIssuingAgency() {
		return issuingAgency;
	}
	public void setIssuingAgency(String issuingAgency) {
		this.issuingAgency = issuingAgency;
	}
	public Date getDateOfReplyFromPia() {
		return dateOfReplyFromPia;
	} 
	public void setDateOfReplyFromPia(Object dateOfReplyFromPia) {
		if(dateOfReplyFromPia instanceof String){
			this.dateOfReplyFromPia = new DateUtil().Correct(dateOfReplyFromPia.toString());
		}
		else{
			this.dateOfReplyFromPia =(Date) dateOfReplyFromPia;
		}		
	}
	public String getReplyFromPia() {
		return replyFromPia;
	}
	public void setReplyFromPia(String replyFromPia) {
		this.replyFromPia = replyFromPia;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCommunicationToPiadate() {
		return communicationToPiadate;
	}	 
	public void setCommunicationToPiadate(Object communicationToPiadate) {
		
		if(communicationToPiadate instanceof String){
			
			this.communicationToPiadate = new DateUtil().Correct(communicationToPiadate.toString());
		}
		else{
			this.communicationToPiadate =(Date) communicationToPiadate;
		}		
	}	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	 
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getReasoncategory() {
		return reasoncategory;
	}
	public void setReasoncategory(String reasoncategory) {
		this.reasoncategory = reasoncategory;
	}
	/*public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public void setDateOfReplyFromPia(Date dateOfReplyFromPia) {
		this.dateOfReplyFromPia = dateOfReplyFromPia;
	}*/
	/*public void setCommunicationToPiadate(Date communicationToPiadate) {
		this.communicationToPiadate = communicationToPiadate;
	}*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ProjectDetailsVO getProjectID() {
		return projectID;
	}
	public void setProjectID(ProjectDetailsVO projectID) {
		this.projectID = projectID;
	}
}
