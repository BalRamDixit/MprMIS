package com.infotech.skills.hbm.piaprofile;
import java.io.Serializable;
import com.infotech.skills.master.MasterVO;

/**
 * @since August 2013
 */
public class PiaDetailVO extends MasterVO implements Serializable {

	private String piaCode;
	private String piaName;
	private String piaRegistrationNo;
	private String categoryCode;
	private String address;
	private String pin;
	private String officePhoneCode;
	private String officePhone;
	private String officeFaxCode;
	private String officeFax;
	private String email;
	private String website;
	private String registrationNumber;
	private String registrationStateCode;
	private String dateOfRegistration;
	private String panNo;
	private String tanNo;
	private String panFileName;
	private String tanFileName;
	private String regFileName;
	private String officePhotoFileName;
	
	private String enrolmentNumber;
	private String enrolmentFileName;
	
	private String piaStatus;
	private String piaConfirmationDate;
	
	private String regNoSection12A;
	private String regDateSection12A;
	private String regNoSection80G;
	private String regDateSection80G;
	private String regNoFCRA;
	private String regDateFCRA;
	private String addressLandBuilding;
	private String freeholdMortgaged;
	
	private String activityCode;
	private String activityName;
	private String categoryName;
	
	private String stateName;
	private String districtName;
	private String blockName;
	private String registrationStateName;
	private String remark;
	
	private String l1Remark;
	
	/**
	 * Same piaCode,piaName property is reused as different property  renamed as piacode,pianame due to 
	 *  case difference for incomplePiaRejection method 
	 * 	at L1 Level in  sql query which gives column names as small characters \;
	 * :P (Not able to understand then please leave ( (^_^) tumse na ho payega (^_^))) 
	 * Genious Coders > Tiwari & Singh
	 */
	private String piacode;
	private String pianame;
	
	
	public String getPiaCode() {
		return piaCode;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getPiaName() {
		return piaName;
	}
	public void setPiaName(String piaName) {
		this.piaName = piaName;
	}
	public String getPiaRegistrationNo() {
		return piaRegistrationNo;
	}
	public void setPiaRegistrationNo(String piaRegistrationNo) {
		this.piaRegistrationNo = piaRegistrationNo;
	}	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getOfficePhoneCode() {
		return officePhoneCode;
	}
	public void setOfficePhoneCode(String officePhoneCode) {
		this.officePhoneCode = officePhoneCode;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getOfficeFaxCode() {
		return officeFaxCode;
	}
	public void setOfficeFaxCode(String officeFaxCode) {
		this.officeFaxCode = officeFaxCode;
	}
	public String getOfficeFax() {
		return officeFax;
	}
	public void setOfficeFax(String officeFax) {
		this.officeFax = officeFax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getRegistrationStateCode() {
		return registrationStateCode;
	}
	public void setRegistrationStateCode(String registrationStateCode) {
		this.registrationStateCode = registrationStateCode;
	}
	public String getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
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
	public void setPanFileName(String panFileName) {
		this.panFileName = panFileName;
	}
	public String getPanFileName() {
		return panFileName;
	}
	public void setTanFileName(String tanFileName) {
		this.tanFileName = tanFileName;
	}
	public String getTanFileName() {
		return tanFileName;
	}
	public void setRegFileName(String regFileName) {
		this.regFileName = regFileName;
	}
	public String getRegFileName() {
		return regFileName;
	}
	public String getPiaStatus() {
		return piaStatus;
	}
	public void setPiaStatus(String piaStatus) {
		this.piaStatus = piaStatus;
	}
	public String getPiaConfirmationDate() {
		return piaConfirmationDate;
	}
	public void setPiaConfirmationDate(String piaConfirmationDate) {
		this.piaConfirmationDate = piaConfirmationDate;
	}
	public String getRegNoSection12A() {
		return regNoSection12A;
	}
	public void setRegNoSection12A(String regNoSection12A) {
		this.regNoSection12A = regNoSection12A;
	}
	public String getRegDateSection12A() {
		return regDateSection12A;
	}
	public void setRegDateSection12A(String regDateSection12A) {
		this.regDateSection12A = regDateSection12A;
	}
	public String getRegNoSection80G() {
		return regNoSection80G;
	}
	public void setRegNoSection80G(String regNoSection80G) {
		this.regNoSection80G = regNoSection80G;
	}
	public String getRegDateSection80G() {
		return regDateSection80G;
	}
	public void setRegDateSection80G(String regDateSection80G) {
		this.regDateSection80G = regDateSection80G;
	}
	public String getRegNoFCRA() {
		return regNoFCRA;
	}
	public void setRegNoFCRA(String regNoFCRA) {
		this.regNoFCRA = regNoFCRA;
	}
	public String getRegDateFCRA() {
		return regDateFCRA;
	}
	public void setRegDateFCRA(String regDateFCRA) {
		this.regDateFCRA = regDateFCRA;
	}
	public String getAddressLandBuilding() {
		return addressLandBuilding;
	}
	public void setAddressLandBuilding(String addressLandBuilding) {
		this.addressLandBuilding = addressLandBuilding;
	}
	public String getFreeholdMortgaged() {
		return freeholdMortgaged;
	}
	public void setFreeholdMortgaged(String freeholdMortgaged) {
		this.freeholdMortgaged = freeholdMortgaged;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		if(activityCode != null){
			activityCode = activityCode.replaceAll("[{}\"]","");
			activityCode = activityCode.replaceAll("[,]",",");
			}
		this.activityCode = activityCode;
	}
	public String getActivityName() {		
		return activityName;
	}
	public void setActivityName(String activityName) {
		if(activityName != null){
		activityName = activityName.replaceAll("[{}\"]","");
		activityName = activityName.replaceAll("[,]",",");
		}
		this.activityName = activityName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getRegistrationStateName() {
		return registrationStateName;
	}
	public void setRegistrationStateName(String registrationStateName) {
		this.registrationStateName = registrationStateName;
	}
	public void setOfficePhotoFileName(String officePhotoFileName) {
		this.officePhotoFileName = officePhotoFileName;
	}
	public String getOfficePhotoFileName() {
		return officePhotoFileName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getL1Remark() {
		return l1Remark;
	}
	public void setL1Remark(String l1Remark) {
		this.l1Remark = l1Remark;
	}
	public String getPiacode() {
		return piacode;
	}
	public void setPiacode(String piacode) {
		this.piacode = piacode;
	}
	public String getPianame() {
		return pianame;
	}
	public void setPianame(String pianame) {
		this.pianame = pianame;
	}
	public String getEnrolmentNumber() {
		return enrolmentNumber;
	}
	public void setEnrolmentNumber(String enrolmentNumber) {
		this.enrolmentNumber = enrolmentNumber;
	}
	public String getEnrolmentFileName() {
		return enrolmentFileName;
	}
	public void setEnrolmentFileName(String enrolmentFileName) {
		this.enrolmentFileName = enrolmentFileName;
	}
	
}
