package com.infotech.skills.hbm.piaproposal;
import java.io.Serializable;
public class MobilizationBenefitsVO implements Serializable{
	private String piaCode;
	private String proposalCode;
	private String noOfMeetings;
	private String noOfRoadShows;
	private String noOfCounselingSession;
	private String noOfOtherAgenciesHiredForMobilization;
	private String drdasLocalLevelAuthorities;
	private String noOfTimesAdvertising;
	private String anyOther;
    private String createdOn;
	private String createdBy;
	private String modifyOn;
	private String modifyBy;
	
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
	public void setNoOfMeetings(String noOfMeetings) {
		this.noOfMeetings = noOfMeetings;
	}
	public String getNoOfMeetings() {
		return noOfMeetings;
	}
	public void setNoOfRoadShows(String noOfRoadShows) {
		this.noOfRoadShows = noOfRoadShows;
	}
	public String getNoOfRoadShows() {
		return noOfRoadShows;
	}
	public void setNoOfCounselingSession(String noOfCounselingSession) {
		this.noOfCounselingSession = noOfCounselingSession;
	}
	public String getNoOfCounselingSession() {
		return noOfCounselingSession;
	}
	public void setNoOfOtherAgenciesHiredForMobilization(
			String noOfOtherAgenciesHiredForMobilization) {
		this.noOfOtherAgenciesHiredForMobilization = noOfOtherAgenciesHiredForMobilization;
	}
	public String getNoOfOtherAgenciesHiredForMobilization() {
		return noOfOtherAgenciesHiredForMobilization;
	}
	public void setDrdasLocalLevelAuthorities(String drdasLocalLevelAuthorities) {
		this.drdasLocalLevelAuthorities = drdasLocalLevelAuthorities;
	}
	public String getDrdasLocalLevelAuthorities() {
		return drdasLocalLevelAuthorities;
	}
	public void setNoOfTimesAdvertising(String noOfTimesAdvertising) {
		this.noOfTimesAdvertising = noOfTimesAdvertising;
	}
	public String getNoOfTimesAdvertising() {
		return noOfTimesAdvertising;
	}
	public void setAnyOther(String anyOther) {
		this.anyOther = anyOther;
	}
	public String getAnyOther() {
		return anyOther;
	}

}
