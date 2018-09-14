package com.infotech.sgsy.monitoringalerts;

import com.infotech.sgsy.common.MasterForm;

public class MonitoringAlertForm extends MasterForm{
	
	public String id;
	public String projectID;	 
	public String typeOfAlert;	
	public String dateOfIssue ;
	public String reasoncategory;
	public String reasonForIssue;
	public String issuingAgency;	
	public String dateOfReplyFromPia;	
    public String replyFromPia;
	public String status;
	public String communicationToPiadate;
	public String remarks;
 
	 
	public String getTypeOfAlert() {
		return typeOfAlert;
	}
	public void setTypeOfAlert(String typeOfAlert) {
		this.typeOfAlert = typeOfAlert;
	}
	public String getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public String getReasonForIssue() {
		return reasonForIssue;
	}
	public void setReasonForIssue(String reasonForIssue) {
		this.reasonForIssue = reasonForIssue;
	}
	 
	public String getDateOfReplyFromPia() {
		return dateOfReplyFromPia;
	}
	public void setDateOfReplyFromPia(String dateOfReplyFromPia) {
		this.dateOfReplyFromPia = dateOfReplyFromPia;
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
	public String getCommunicationToPiadate() {
		return communicationToPiadate;
	}
	public void setCommunicationToPiadate(String communicationToPiadate) {
		this.communicationToPiadate = communicationToPiadate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getIssuingAgency() {
		return issuingAgency;
	}
	public void setIssuingAgency(String issuingAgency) {
		this.issuingAgency = issuingAgency;
	}
	public String getReasoncategory() {
		return reasoncategory;
	}
	public void setReasoncategory(String reasoncategory) {
		this.reasoncategory = reasoncategory;
	} 
	 
}
