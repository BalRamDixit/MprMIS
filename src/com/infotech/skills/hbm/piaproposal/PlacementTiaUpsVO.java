package com.infotech.skills.hbm.piaproposal;

import java.io.Serializable;

public class PlacementTiaUpsVO implements Serializable{
	
	private String piaCode;
	private String praposalCode;
	private	String empName; 
	private String registrationNo; 
	private String panNo;
	private String tanNo;
	private String website;
	private String nameContactPerson; 
	private String contactNo;
	private String emailId;
	private String noOfTrainee; 
	private String confirmationAttached; 
	private String createdOn;
	private String createdBy;
	private String modifyOn;
	private String modifyBy;
	
	public PlacementTiaUpsVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlacementTiaUpsVO(String piaCode, String praposalCode,
			String empName, String registrationNo, String panNo, String tanNo,
			String website, String nameContactPerson, String contactNo,
			String emailId, String noOfTrainee, String confirmationAttached,
			String createdOn, String createdBy, String modifyOn, String modifyBy) {
		super();
		this.piaCode = piaCode;
		this.praposalCode = praposalCode;
		this.empName = empName;
		this.registrationNo = registrationNo;
		this.panNo = panNo;
		this.tanNo = tanNo;
		this.website = website;
		this.nameContactPerson = nameContactPerson;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.noOfTrainee = noOfTrainee;
		this.confirmationAttached = confirmationAttached;
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getTanNo() {
		return tanNo;
	}

	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNameContactPerson() {
		return nameContactPerson;
	}

	public void setNameContactPerson(String nameContactPerson) {
		this.nameContactPerson = nameContactPerson;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getNoOfTrainee() {
		return noOfTrainee;
	}

	public void setNoOfTrainee(String noOfTrainee) {
		this.noOfTrainee = noOfTrainee;
	}

	public String getConfirmationAttached() {
		return confirmationAttached;
	}

	public void setConfirmationAttached(String confirmationAttached) {
		this.confirmationAttached = confirmationAttached;
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
