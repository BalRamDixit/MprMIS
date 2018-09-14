package com.infotech.skills.hbm.piaproposal;
 
import java.io.Serializable;

public class TrainingTargetsVO implements Serializable
 {
	private String piaCode;
	private String proposalCode;
	private String year;
	private  String month ;
	private  String batch ;
	private  String  trade;
	private  String noOfCandidates;
	private String createdOn ;
	private String createdBy;
	private  String modifyOn ;
	private  String modifyBy ;
	
	public void setYear(String year) {
		this.year = year;
	}
	public String getYear() {
		return year;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonth() {
		return month;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getBatch() {
		return batch;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getTrade() {
		return trade;
	}
	public void setNoOfCandidates(String noOfCandidates) {
		this.noOfCandidates = noOfCandidates;
	}
	public String getNoOfCandidates() {
		return noOfCandidates;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
	public String getModifyOn() {
		return modifyOn;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getPiaCode() {
		return piaCode;
	}
	public void setProposalCode(String proposalCode) {
		this.proposalCode = proposalCode;
	}
	public String getProposalCode() {
		return proposalCode;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
