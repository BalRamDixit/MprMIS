package com.infotech.sgsy.stateSetup.hrTeam;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.master.designationMaster.DesignationMasterVo;
import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.masterdata.projectTypeMaster.ProjectTypeMasterVO;
import com.infotech.sgsy.util.DateUtil;

public class HRTeamVO extends MasterVO implements Serializable {

	private String id;
	//private String entityCode;
	private String personName;
	//public String designation;
	private String location;
	/*public String  districtCode ;*/
	
	private DesignationMasterVo designation;
	private DistrictMasterVO district;
	
	
	//public String  joiningDate ;
	
	public Date  joiningDate ;
	
	private String  email ;
	private String  officeNo ;
	private String  mobileNo ;
	private String  eSopCertReq ;
	private String  eSopCertLevel ;
	
	
	private Date  certificationDate ;
	private String  isActive ;
	
	
	private Date dateOfLeaving;
	
	private StateVO state;
	
	private String createdBy;
	private Date createdOn;
	public ProjectTypeMasterVO getThematic() {
		return thematic;
	}
	public void setThematic(ProjectTypeMasterVO thematic) {
		this.thematic = thematic;
	}
	private String updatedBy;
	private Date updatedOn;
	private String CertNo;
	private ProjectTypeMasterVO thematic;
	
	

	
	
	
	
	
	
	
/*	public String getThematic() {
		return thematic;
	}
	public void setThematic(String thematic) {
		this.thematic = thematic;
	}*/
	public String getCertNo() {
		return CertNo;
	}
	public void setCertNo(String certNo) {
		CertNo = certNo;
	}
	public StateVO getState() {
		return state;
	}
	public void setState(StateVO state) {
		this.state = state;
	}
	public DistrictMasterVO getDistrict() {
		return district;
	}
	public void setDistrict(DistrictMasterVO district) {
		this.district = district;
	}
	public DesignationMasterVo getDesignation() {
		return designation;
	}
	public void setDesignation(DesignationMasterVo designation) {
		this.designation = designation;
	}
	public Date getDateOfLeaving() {
		return dateOfLeaving;
	}
	public void setDateOfLeaving(Object dateOfLeaving) {
		if(dateOfLeaving instanceof String){
			this.dateOfLeaving = new DateUtil().Correct(dateOfLeaving.toString());
		}else{
			this.dateOfLeaving=(Date) dateOfLeaving;
		}
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public Date getCertificationDate() {
		return certificationDate;
	}
	public void setCertificationDate(Object certificationDate) {
		
		if(certificationDate instanceof String){
			this.certificationDate = new DateUtil().Correct(certificationDate.toString());
		}else{
			this.certificationDate=(Date) certificationDate;
		}
		
	}
	public void setJoiningDate(Object joiningDate) {
		if(joiningDate instanceof String){
		this.joiningDate =new DateUtil().Correct(joiningDate.toString());
		}else{
			this.joiningDate=(Date) joiningDate;
		}
	}
	
	/*public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}*/
	/*public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}*/
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	/*public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}*/
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	/*public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}*/
	/*public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}*/
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
	
	/*public String getCertificationDate() {
		return certificationDate;
	}
	public void setCertificationDate(String certificationDate) {
		this.certificationDate = certificationDate;
	}*/
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
