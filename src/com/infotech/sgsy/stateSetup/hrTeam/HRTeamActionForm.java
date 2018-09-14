package com.infotech.sgsy.stateSetup.hrTeam;

import org.apache.struts.action.ActionForm;

public class HRTeamActionForm extends ActionForm {

	public String id;
	public String entityCode;
	public String personName;
	public String designation;
	public String location;
	public String  districtCode ;
	public String  joiningDate ;
	public String  email ;
	public String  officeNo ;
	public String  mobileNo ;
	public String  eSopCertReq ;
	public String  eSopCertLevel ;
	public String  certificationDate ;
	public String  isActive ;
	
	private String dateOfLeaving;
	private String certNo;
	private String thematic;
	
	
	public String getThematic() {
		return thematic;
	}
	public void setThematic(String thematic) {
		this.thematic = thematic;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getDateOfLeaving() {
		return dateOfLeaving;
	}
	public void setDateOfLeaving(String dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOfficeNo() {
		return officeNo;
	}
	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String geteSopCertReq() {
		return eSopCertReq;
	}
	public void seteSopCertReq(String eSopCertReq) {
		this.eSopCertReq = eSopCertReq;
	}
	public String geteSopCertLevel() {
		return eSopCertLevel;
	}
	public void seteSopCertLevel(String eSopCertLevel) {
		this.eSopCertLevel = eSopCertLevel;
	}
	public String getCertificationDate() {
		return certificationDate;
	}
	public void setCertificationDate(String certificationDate) {
		this.certificationDate = certificationDate;
	}
	
	
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
