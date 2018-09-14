package com.infotech.skills.proposal.verifypia;

import org.apache.struts.upload.FormFile;

import com.infotech.skills.master.MasterForm;

public class ModifyPiaForm extends MasterForm{
	
	
	private static final long serialVersionUID = -1621821640134578191L;
	
	private String piaCode;
	private String piaStatus;
	private String piaName;
	private String piaConfirmationDate;
	private String piaRegistrationNo;
	private String[] piaActivity;
	private String categoryCode;
	private String name;
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
	private String tanNo;
	private String panNo;
	private String regFileName;
	private String officePhotoFileName;
	private String panFileName;
	private String tanFileName;
	private String authCVFileName;
	private String authPhotoFileName;
	private FormFile formFile;
	
	private FormFile officePhotoFile;
	private FormFile panFile;
	private FormFile tanFile;
	private FormFile regFile;
	
	private String regNoSection12A;
	private String regDateSection12A;
	private String regNoSection80G;
	private String regDateSection80G;
	private String regNoFCRA;
	private String regDateFCRA;
	private String addressLandBuilding;
	private String freeholdMortgaged;
	
	private String[] memberCode;
	private String[] memberName;
	private String[] memberContact;
	private String[] memberDesignation;
	private String[] memberLiability;
	private String[] memberEmail;
	private String[] memberPan;
	private String[] memberAadharVoterNo;
	private String[] memberPassportDrivingNo;
	private String[] nriCode;
	private String[] nriName;
	private String[] nriCountry;
	private String[] nriPassportNo;
	private String[] nriPassportValidDate;
	private String[] nriVisaValidDate;
	private String[] nriWorkingPermitDate;
	private String[] nriWorkPermit;
	private String[] nriclearanceFRAMHA;
	private String[] nriStatus;
	private String[] authorizedPerson;
	
	private String authPersonCode;
	private String authPersonName;
	private String authPersonContact;
	private String authPersonPan;
	private String authPersonEmail;
	private String authPersonAdhaar;
	private String authPersonPassport;
	private String authPersonCV;
	private String authPersonPhoto;
	private FormFile cvFile;
	private FormFile photoFile;
	
	private String authRelativeName;
	private String authAge;
	private String authOccupation;
	private String authDesignation;
	private String authAddress;
	private String authPostOffice;
	private String authPoliceStation;
	private String authStateCode;
	private String authPlace;
	private String authDate;
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
	public String[] getPiaActivity() {
		return piaActivity;
	}
	public void setPiaActivity(String[] piaActivity) {
		this.piaActivity = piaActivity;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getTanNo() {
		return tanNo;
	}
	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getOfficePhotoFileName() {
		return officePhotoFileName;
	}
	public void setOfficePhotoFileName(String officePhotoFileName) {
		this.officePhotoFileName = officePhotoFileName;
	}
	public String getPanFileName() {
		return panFileName;
	}
	public void setPanFileName(String panFileName) {
		this.panFileName = panFileName;
	}
	public String getTanFileName() {
		return tanFileName;
	}
	public void setTanFileName(String tanFileName) {
		this.tanFileName = tanFileName;
	}
	public String getAuthCVFileName() {
		return authCVFileName;
	}
	public void setAuthCVFileName(String authCVFileName) {
		this.authCVFileName = authCVFileName;
	}
	public String getAuthPhotoFileName() {
		return authPhotoFileName;
	}
	public void setAuthPhotoFileName(String authPhotoFileName) {
		this.authPhotoFileName = authPhotoFileName;
	}
	public FormFile getFormFile() {
		return formFile;
	}
	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}
	public FormFile getOfficePhotoFile() {
		return officePhotoFile;
	}
	public void setOfficePhotoFile(FormFile officePhotoFile) {
		this.officePhotoFile = officePhotoFile;
	}
	public FormFile getPanFile() {
		return panFile;
	}
	public void setPanFile(FormFile panFile) {
		this.panFile = panFile;
	}
	public FormFile getTanFile() {
		return tanFile;
	}
	public void setTanFile(FormFile tanFile) {
		this.tanFile = tanFile;
	}
	public FormFile getRegFile() {
		return regFile;
	}
	public void setRegFile(FormFile regFile) {
		this.regFile = regFile;
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
	public String[] getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String[] memberCode) {
		this.memberCode = memberCode;
	}
	public String[] getMemberName() {
		return memberName;
	}
	public void setMemberName(String[] memberName) {
		this.memberName = memberName;
	}
	public String[] getMemberContact() {
		return memberContact;
	}
	public void setMemberContact(String[] memberContact) {
		this.memberContact = memberContact;
	}
	public String[] getMemberDesignation() {
		return memberDesignation;
	}
	public void setMemberDesignation(String[] memberDesignation) {
		this.memberDesignation = memberDesignation;
	}
	public String[] getMemberLiability() {
		return memberLiability;
	}
	public void setMemberLiability(String[] memberLiability) {
		this.memberLiability = memberLiability;
	}
	public String[] getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String[] memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String[] getMemberPan() {
		return memberPan;
	}
	public void setMemberPan(String[] memberPan) {
		this.memberPan = memberPan;
	}
	public String[] getMemberAadharVoterNo() {
		return memberAadharVoterNo;
	}
	public void setMemberAadharVoterNo(String[] memberAadharVoterNo) {
		this.memberAadharVoterNo = memberAadharVoterNo;
	}
	public String[] getMemberPassportDrivingNo() {
		return memberPassportDrivingNo;
	}
	public void setMemberPassportDrivingNo(String[] memberPassportDrivingNo) {
		this.memberPassportDrivingNo = memberPassportDrivingNo;
	}
	public String[] getNriCode() {
		return nriCode;
	}
	public void setNriCode(String[] nriCode) {
		this.nriCode = nriCode;
	}
	public String[] getNriName() {
		return nriName;
	}
	public void setNriName(String[] nriName) {
		this.nriName = nriName;
	}
	public String[] getNriCountry() {
		return nriCountry;
	}
	public void setNriCountry(String[] nriCountry) {
		this.nriCountry = nriCountry;
	}
	public String[] getNriPassportNo() {
		return nriPassportNo;
	}
	public void setNriPassportNo(String[] nriPassportNo) {
		this.nriPassportNo = nriPassportNo;
	}
	public String[] getNriPassportValidDate() {
		return nriPassportValidDate;
	}
	public void setNriPassportValidDate(String[] nriPassportValidDate) {
		this.nriPassportValidDate = nriPassportValidDate;
	}
	public String[] getNriVisaValidDate() {
		return nriVisaValidDate;
	}
	public void setNriVisaValidDate(String[] nriVisaValidDate) {
		this.nriVisaValidDate = nriVisaValidDate;
	}
	public String[] getNriWorkingPermitDate() {
		return nriWorkingPermitDate;
	}
	public void setNriWorkingPermitDate(String[] nriWorkingPermitDate) {
		this.nriWorkingPermitDate = nriWorkingPermitDate;
	}
	public String[] getNriWorkPermit() {
		return nriWorkPermit;
	}
	public void setNriWorkPermit(String[] nriWorkPermit) {
		this.nriWorkPermit = nriWorkPermit;
	}
	public String[] getNriclearanceFRAMHA() {
		return nriclearanceFRAMHA;
	}
	public void setNriclearanceFRAMHA(String[] nriclearanceFRAMHA) {
		this.nriclearanceFRAMHA = nriclearanceFRAMHA;
	}
	public String[] getNriStatus() {
		return nriStatus;
	}
	public void setNriStatus(String[] nriStatus) {
		this.nriStatus = nriStatus;
	}
	public String[] getAuthorizedPerson() {
		return authorizedPerson;
	}
	public void setAuthorizedPerson(String[] authorizedPerson) {
		this.authorizedPerson = authorizedPerson;
	}
	public String getAuthPersonCode() {
		return authPersonCode;
	}
	public void setAuthPersonCode(String authPersonCode) {
		this.authPersonCode = authPersonCode;
	}
	public String getAuthPersonName() {
		return authPersonName;
	}
	public void setAuthPersonName(String authPersonName) {
		this.authPersonName = authPersonName;
	}
	public String getAuthPersonContact() {
		return authPersonContact;
	}
	public void setAuthPersonContact(String authPersonContact) {
		this.authPersonContact = authPersonContact;
	}
	public String getAuthPersonPan() {
		return authPersonPan;
	}
	public void setAuthPersonPan(String authPersonPan) {
		this.authPersonPan = authPersonPan;
	}
	public String getAuthPersonEmail() {
		return authPersonEmail;
	}
	public void setAuthPersonEmail(String authPersonEmail) {
		this.authPersonEmail = authPersonEmail;
	}
	public String getAuthPersonAdhaar() {
		return authPersonAdhaar;
	}
	public void setAuthPersonAdhaar(String authPersonAdhaar) {
		this.authPersonAdhaar = authPersonAdhaar;
	}
	public String getAuthPersonPassport() {
		return authPersonPassport;
	}
	public void setAuthPersonPassport(String authPersonPassport) {
		this.authPersonPassport = authPersonPassport;
	}
	public String getAuthPersonCV() {
		return authPersonCV;
	}
	public void setAuthPersonCV(String authPersonCV) {
		this.authPersonCV = authPersonCV;
	}
	public String getAuthPersonPhoto() {
		return authPersonPhoto;
	}
	public void setAuthPersonPhoto(String authPersonPhoto) {
		this.authPersonPhoto = authPersonPhoto;
	}
	public FormFile getCvFile() {
		return cvFile;
	}
	public void setCvFile(FormFile cvFile) {
		this.cvFile = cvFile;
	}
	public FormFile getPhotoFile() {
		return photoFile;
	}
	public void setPhotoFile(FormFile photoFile) {
		this.photoFile = photoFile;
	}
	public String getAuthRelativeName() {
		return authRelativeName;
	}
	public void setAuthRelativeName(String authRelativeName) {
		this.authRelativeName = authRelativeName;
	}
	public String getAuthAge() {
		return authAge;
	}
	public void setAuthAge(String authAge) {
		this.authAge = authAge;
	}
	public String getAuthOccupation() {
		return authOccupation;
	}
	public void setAuthOccupation(String authOccupation) {
		this.authOccupation = authOccupation;
	}
	public String getAuthDesignation() {
		return authDesignation;
	}
	public void setAuthDesignation(String authDesignation) {
		this.authDesignation = authDesignation;
	}
	public String getAuthAddress() {
		return authAddress;
	}
	public void setAuthAddress(String authAddress) {
		this.authAddress = authAddress;
	}
	public String getAuthPostOffice() {
		return authPostOffice;
	}
	public void setAuthPostOffice(String authPostOffice) {
		this.authPostOffice = authPostOffice;
	}
	public String getAuthPoliceStation() {
		return authPoliceStation;
	}
	public void setAuthPoliceStation(String authPoliceStation) {
		this.authPoliceStation = authPoliceStation;
	}
	public String getAuthStateCode() {
		return authStateCode;
	}
	public void setAuthStateCode(String authStateCode) {
		this.authStateCode = authStateCode;
	}
	public String getAuthPlace() {
		return authPlace;
	}
	public void setAuthPlace(String authPlace) {
		this.authPlace = authPlace;
	}
	public String getAuthDate() {
		return authDate;
	}
	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}
	public String getPiaCode() {
		return piaCode;
	}
	public void setRegFileName(String regFileName) {
		this.regFileName = regFileName;
	}
	public String getRegFileName() {
		return regFileName;
	}
	public void setPiaConfirmationDate(String piaConfirmationDate) {
		this.piaConfirmationDate = piaConfirmationDate;
	}
	public String getPiaConfirmationDate() {
		return piaConfirmationDate;
	}
	public String getPiaStatus() {
		return piaStatus;
	}
	public void setPiaStatus(String piaStatus) {
		this.piaStatus = piaStatus;
	}
	
}
