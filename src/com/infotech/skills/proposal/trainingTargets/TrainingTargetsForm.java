package com.infotech.skills.proposal.trainingTargets;

import com.infotech.skills.master.MasterForm;
 
public  class TrainingTargetsForm extends MasterForm{
	
	private String piaCode;
	private String proposalCode;
	private String year;
	private  String month ;
	private  String[] batch;
	private  String[] trade;
	private  String[] noOfCandidates;
	private String createdOn ;
	private String createdBy;
	private  String modifyOn ;
	private  String modifyBy ;
	public String getPiaCode() {
		return piaCode;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getProposalCode() {
		return proposalCode;
	}
	public void setProposalCode(String proposalCode) {
		this.proposalCode = proposalCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifyOn() {
		return modifyOn;
	}
	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public void setBatch(String[] batch) {
		this.batch = batch;
	}
	public String[] getBatch() {
		return batch;
	}
	public void setTrade(String[] trade) {
		this.trade = trade;
	}
	public String[] getTrade() {
		return trade;
	}
	public void setNoOfCandidates(String[] noOfCandidates) {
		this.noOfCandidates = noOfCandidates;
	}
	public String[] getNoOfCandidates() {
		return noOfCandidates;
	}
	
}