package com.infotech.sgsy.monitoringpenalty;

import com.infotech.sgsy.common.MasterForm;

public class MonitoringPenaltyForm extends MasterForm{


		private  String id;
	    private String projectID;
	    private String typeOfPenalty ;	
	    private String reasonForPenalty ;
	    private String status;
	    private String dateofIssue ;	
	    private String lastDateOfAppeal ;	
	    private String actualDateOfAppeal ;
	    private String lastDateOfAppealDisposal ;
	    private String actualDateOfAppealDisposal;
	    private String appealDisposalResult;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getProjectID() {
			return projectID;
		}
		public void setProjectID(String projectID) {
			this.projectID = projectID;
		}
		public String getTypeOfPenalty() {
			return typeOfPenalty;
		}
		public void setTypeOfPenalty(String typeOfPenalty) {
			this.typeOfPenalty = typeOfPenalty;
		}
		public String getReasonForPenalty() {
			return reasonForPenalty;
		}
		public void setReasonForPenalty(String reasonForPenalty) {
			this.reasonForPenalty = reasonForPenalty;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDateofIssue() {
			return dateofIssue;
		}
		public void setDateofIssue(String dateofIssue) {
			this.dateofIssue = dateofIssue;
		}
		public String getLastDateOfAppeal() {
			return lastDateOfAppeal;
		}
		public void setLastDateOfAppeal(String lastDateOfAppeal) {
			this.lastDateOfAppeal = lastDateOfAppeal;
		}
		public String getActualDateOfAppeal() {
			return actualDateOfAppeal;
		}
		public void setActualDateOfAppeal(String actualDateOfAppeal) {
			this.actualDateOfAppeal = actualDateOfAppeal;
		}
		public String getLastDateOfAppealDisposal() {
			return lastDateOfAppealDisposal;
		}
		public void setLastDateOfAppealDisposal(String lastDateOfAppealDisposal) {
			this.lastDateOfAppealDisposal = lastDateOfAppealDisposal;
		}
		public String getActualDateOfAppealDisposal() {
			return actualDateOfAppealDisposal;
		}
		public void setActualDateOfAppealDisposal(String actualDateOfAppealDisposal) {
			this.actualDateOfAppealDisposal = actualDateOfAppealDisposal;
		}
		public String getAppealDisposalResult() {
			return appealDisposalResult;
		}
		public void setAppealDisposalResult(String appealDisposalResult) {
			this.appealDisposalResult = appealDisposalResult;
		}
	   
		 
		
	}
