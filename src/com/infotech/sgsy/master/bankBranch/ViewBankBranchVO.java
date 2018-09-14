package com.infotech.sgsy.master.bankBranch;

import java.io.Serializable;
import com.infotech.sgsy.common.MasterVO;

public  class ViewBankBranchVO extends MasterVO implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	private String entityCode;
	private String bankLevelCode;
	private String bankCode;
	private String bankBranchCode;
	private String branchLevel;
	private String stateName;
	private String districtName;
	private String bankName;
	private String bankBranchName;
	private String ifscCode;
	private String address;
	
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}	
	public String getBankLevelCode() {
		return bankLevelCode;
	}
	public void setBankLevelCode(String bankLevelCode) {
		this.bankLevelCode = bankLevelCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankBranchCode() {
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}
	public String getBranchLevel() {
		return branchLevel;
	}
	public void setBranchLevel(String branchLevel) {
		this.branchLevel = branchLevel;
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
