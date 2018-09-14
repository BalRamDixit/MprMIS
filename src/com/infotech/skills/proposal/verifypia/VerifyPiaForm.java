package com.infotech.skills.proposal.verifypia;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.common.MasterForm;
public class VerifyPiaForm extends MasterForm{
	
	private static final long serialVersionUID = 7240122082913861613L;
	private String piaCode;
	private String regFileName;
	private String piaApplicationStatus; 
	private String remark;
	private String[] piaList;
	
	//for PRN Modification
	private String categoryId;
	private String prnNumber;
	private String email;
	private String piaName;
	private String categoryName;
	private String panNo;
	private String tanNo;
	private String registrationNumber;
	private String address;

	public String getPiaCode() {
		return piaCode;
	}
	public void setPiaCode(String piaCode) {
		this.piaCode = piaCode;
	}

	public void setPiaList(String[] piaList) {
		this.piaList = piaList;
	}
	public String[] getPiaList() {
		return piaList;
	}

	public void setRegFileName(String regFileName) {
		this.regFileName = regFileName;
	}
	public String getRegFileName() {
		return regFileName;
	}
	public String getPiaApplicationStatus() {
		return piaApplicationStatus;
	}
	public void setPiaApplicationStatus(String piaApplicationStatus) {
		this.piaApplicationStatus = piaApplicationStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {	
		this.setPiaCode("");
		this.setRegFileName("");
		this.setPiaApplicationStatus("") ;
		this.setRemark("");
	}
	
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getPrnNumber() {
		return prnNumber;
	}
	public void setPrnNumber(String prnNumber) {
		this.prnNumber = prnNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPiaName() {
		return piaName;
	}
	public void setPiaName(String piaName) {
		this.piaName = piaName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
