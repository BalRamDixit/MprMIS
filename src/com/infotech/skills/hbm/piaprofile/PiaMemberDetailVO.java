package com.infotech.skills.hbm.piaprofile;
 
import java.io.Serializable;

import com.infotech.skills.master.MasterVO;

public class PiaMemberDetailVO extends MasterVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @since  August 2013  
	 */
	private String memberCode;
	private String piaCode;
    private String memberName;
	private String contact;
	private String designation;
	private String liability;
	private String email;
	private String pan;
	private String aadharVoterNo;
	private String passportDrivingNo;
	private String country;
	private String passportValidDate;
	private String visaValidDate;
	private String workingPermitDate;
	private String workPermit;
	private String clearanceFRAMHA; 
    private String nriStatus;
    private String authorizedPerson;
	
    private String cvFileName;
	private String photoFileName;
    
    private String relativeName;
    private String age;
    private String occupation;
    private String address;
    private String postOffice;
    private String policeStation;
    private String stateName;
    private String place;
    
    private String isNonProfitOrg;
  
   
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getPiaCode() {
		return piaCode;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}	
	public String getLiability() {
		return liability;
	}
	public void setLiability(String liability) {
		this.liability = liability;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAadharVoterNo() {
		return aadharVoterNo;
	}
	public void setAadharVoterNo(String aadharVoterNo) {
		this.aadharVoterNo = aadharVoterNo;
	}
	public String getPassportDrivingNo() {
		return passportDrivingNo;
	}
	public void setPassportDrivingNo(String passportDrivingNo) {
		this.passportDrivingNo = passportDrivingNo;
	}	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPassportValidDate() {
		return passportValidDate;
	}
	public void setPassportValidDate(String passportValidDate) {
		this.passportValidDate = passportValidDate;
	}
	public String getVisaValidDate() {
		return visaValidDate;
	}
	public void setVisaValidDate(String visaValidDate) {
		this.visaValidDate = visaValidDate;
	}
	public String getWorkingPermitDate() {
		return workingPermitDate;
	}
	public void setWorkingPermitDate(String workingPermitDate) {
		this.workingPermitDate = workingPermitDate;
	}
	public String getWorkPermit() {
		return workPermit;
	}
	public void setWorkPermit(String workPermit) {
		this.workPermit = workPermit;
	}
	public String getClearanceFRAMHA() {
		return clearanceFRAMHA;
	}
	public void setClearanceFRAMHA(String clearanceFRAMHA) {
		this.clearanceFRAMHA = clearanceFRAMHA;
	}
	public String getNriStatus() {
		return nriStatus;
	}
	public void setNriStatus(String nriStatus) {
		this.nriStatus = nriStatus;
	}
	public String getAuthorizedPerson() {
		return authorizedPerson;
	}
	public void setAuthorizedPerson(String authorizedPerson) {
		this.authorizedPerson = authorizedPerson;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostOffice() {
		return postOffice;
	}
	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getCvFileName() {
		return cvFileName;
	}
	public void setCvFileName(String cvFileName) {
		this.cvFileName = cvFileName;
	}
	public String getPhotoFileName() {
		return photoFileName;
	}
	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public String getIsNonProfitOrg() {
		return isNonProfitOrg;
	}
	public void setIsNonProfitOrg(String isNonProfitOrg) {
		this.isNonProfitOrg = isNonProfitOrg;
	}
	
	
			 
}
