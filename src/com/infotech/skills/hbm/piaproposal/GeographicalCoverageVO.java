package com.infotech.skills.hbm.piaproposal;

import java.io.Serializable;
public class GeographicalCoverageVO  implements Serializable{
	private String piaCode;
	private String proposalCode;
	private String stateName;
	private String districtName;
	private String noOfDistricts;
	private String projectDuration;
	private String createdOn;
	private String createdBy;
	private String modifyOn;
	private String modifyBy;
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
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setNoOfDistricts(String noOfDistricts) {
		this.noOfDistricts = noOfDistricts;
	}
	public String getNoOfDistricts() {
		return noOfDistricts;
	}
	public void setProjectDuration(String projectDuration) {
		this.projectDuration = projectDuration;
	}
	public String getProjectDuration() {
		return projectDuration;
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

}
