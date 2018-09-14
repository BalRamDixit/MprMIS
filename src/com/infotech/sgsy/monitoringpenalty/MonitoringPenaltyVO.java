package com.infotech.sgsy.monitoringpenalty;

import java.util.Date;

import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.util.DateUtil;

public class MonitoringPenaltyVO {

	    private String id;
	    private ProjectDetailsVO projectID;
	    private String typeOfPenalty ;	
	    private String reasonForPenalty ;
	    private String status;
	    private Date dateofIssue ;	
	    private Date lastDateOfAppeal ;	
	    private Date actualDateOfAppeal ;
	    private Date lastDateOfAppealDisposal ;
	    private Date actualDateOfAppealDisposal;
	    private String appealDisposalResult;
		
	    private String createdBy;
		private Date createdOn;
		private String updatedBy;
		private Date updatedOn;
		
	
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
		 
		 
		public String getAppealDisposalResult() {
			return appealDisposalResult;
		}
		public void setAppealDisposalResult(String appealDisposalResult) {
			this.appealDisposalResult = appealDisposalResult;
		}
		 
		public Date getDateofIssue() {
			return dateofIssue;
		}
		/*public void setDateofIssue(Date dateofIssue) {
			this.dateofIssue = dateofIssue;
		}*/
		public void setDateofIssue(Object dateofIssue) {
	 		if(dateofIssue instanceof String){
				this.dateofIssue = new DateUtil().Correct(dateofIssue.toString());
			}
			else{
				this.dateofIssue =(Date) dateofIssue;
			}	 
		}  
		public Date getLastDateOfAppeal() {
			return lastDateOfAppeal;
		}
		/*public void setLastDateOfAppeal(Date lastDateOfAppeal) {
			this.lastDateOfAppeal = lastDateOfAppeal;
		}*/
		public void setLastDateOfAppeal(Object lastDateOfAppeal) {
	 		if(lastDateOfAppeal instanceof String){
				this.lastDateOfAppeal = new DateUtil().Correct(lastDateOfAppeal.toString());
			}
			else{
				this.lastDateOfAppeal =(Date) lastDateOfAppeal;
			}	 
		}  
		public Date getActualDateOfAppeal() {
			return actualDateOfAppeal;
		}
		/*public void setActualDateOfAppeal(Date actualDateOfAppeal) {
			this.actualDateOfAppeal = actualDateOfAppeal;
		}*/
		
		public void setActualDateOfAppeal(Object actualDateOfAppeal) {
	 		if(actualDateOfAppeal instanceof String){
				this.actualDateOfAppeal = new DateUtil().Correct(actualDateOfAppeal.toString());
			}
			else{
				this.actualDateOfAppeal =(Date) actualDateOfAppeal;
			}	 
		}  
		public Date getLastDateOfAppealDisposal() {
			return lastDateOfAppealDisposal;
		}
		/*public void setLastDateOfAppealDisposal(Date lastDateOfAppealDisposal) {
			this.lastDateOfAppealDisposal = lastDateOfAppealDisposal;
		}*/
		public void setLastDateOfAppealDisposal(Object lastDateOfAppealDisposal) {
	 		if(lastDateOfAppealDisposal instanceof String){
				this.lastDateOfAppealDisposal = new DateUtil().Correct(lastDateOfAppealDisposal.toString());
			}
			else{
				this.lastDateOfAppealDisposal =(Date) lastDateOfAppealDisposal;
			}	 
		} 
		public Date getActualDateOfAppealDisposal() {
			return actualDateOfAppealDisposal;
		}
		/*public void setActualDateOfAppealDisposal(Date actualDateOfAppealDisposal) {
			this.actualDateOfAppealDisposal = actualDateOfAppealDisposal;
		}*/
		
		public void setActualDateOfAppealDisposal(Object actualDateOfAppealDisposal) {
	 		if(actualDateOfAppealDisposal instanceof String){
				this.actualDateOfAppealDisposal = new DateUtil().Correct(actualDateOfAppealDisposal.toString());
			}
			else{
				this.actualDateOfAppealDisposal =(Date) actualDateOfAppealDisposal;
			}	 
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
