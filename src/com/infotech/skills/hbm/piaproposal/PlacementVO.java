package com.infotech.skills.hbm.piaproposal;

import java.io.Serializable;

public class PlacementVO implements Serializable{
	
	private String piaCode;
	private String praposalCode;
	private String captivelyBeneficiaries;
	private String organizedBeneficiaries;
	private String involvementAgencies;
	private String facilitationBeneficiaries;
	private String reemploymentBeneficiaries;
	private String createdOn;
	private String createdBy;
	private String modifyOn;
	private String modifyBy;
	
	public PlacementVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlacementVO(String piaCode, String praposalCode,
			String captivelyBeneficiaries, String organizedBeneficiaries,
			String involvementAgencies, String facilitationBeneficiaries,
			String reemploymentBeneficiaries, String createdOn,
			String createdBy, String modifyOn, String modifyBy) {
		super();
		this.piaCode = piaCode;
		this.praposalCode = praposalCode;
		this.captivelyBeneficiaries = captivelyBeneficiaries;
		this.organizedBeneficiaries = organizedBeneficiaries;
		this.involvementAgencies = involvementAgencies;
		this.facilitationBeneficiaries = facilitationBeneficiaries;
		this.reemploymentBeneficiaries = reemploymentBeneficiaries;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifyOn = modifyOn;
		this.modifyBy = modifyBy;
	}

	public String getPiaCode() {
		return piaCode;
	}

	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}

	public String getPraposalCode() {
		return praposalCode;
	}

	public void setPraposalCode(String praposalCode) {
		this.praposalCode = praposalCode;
	}

	public String getCaptivelyBeneficiaries() {
		return captivelyBeneficiaries;
	}

	public void setCaptivelyBeneficiaries(String captivelyBeneficiaries) {
		this.captivelyBeneficiaries = captivelyBeneficiaries;
	}

	public String getOrganizedBeneficiaries() {
		return organizedBeneficiaries;
	}

	public void setOrganizedBeneficiaries(String organizedBeneficiaries) {
		this.organizedBeneficiaries = organizedBeneficiaries;
	}

	public String getInvolvementAgencies() {
		return involvementAgencies;
	}

	public void setInvolvementAgencies(String involvementAgencies) {
		this.involvementAgencies = involvementAgencies;
	}

	public String getFacilitationBeneficiaries() {
		return facilitationBeneficiaries;
	}

	public void setFacilitationBeneficiaries(String facilitationBeneficiaries) {
		this.facilitationBeneficiaries = facilitationBeneficiaries;
	}

	public String getReemploymentBeneficiaries() {
		return reemploymentBeneficiaries;
	}

	public void setReemploymentBeneficiaries(String reemploymentBeneficiaries) {
		this.reemploymentBeneficiaries = reemploymentBeneficiaries;
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

}
