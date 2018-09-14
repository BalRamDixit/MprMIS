package com.infotech.sgsy.master.bankBranch;

import java.io.Serializable;
import com.infotech.sgsy.common.MasterVO;

public  class BankBranchVO extends MasterVO implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	private String entityCode ;
	private String bankBranchCode ;
	private String bankCode;
	private String bankBranchName ;
	private String ifscCode ;
	private String activeFlag;
	private String bankName;
	private String bankTypeCode;
	private String bankTypeDetails;
	private String pk_mst_bank_branch;	
	private String levelFlag;
	private String blockName;
	private String flag;
	private String districtName;
	private String address;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getPk_mst_bank_branch() {
		return pk_mst_bank_branch;
	}
	public void setPk_mst_bank_branch(String pk_mst_bank_branch) {
		this.pk_mst_bank_branch = pk_mst_bank_branch;
	}	 
	public String getBankBranchCode() {
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankTypeCode() {
		return bankTypeCode;
	}
	public void setBankTypeCode(String bankTypeCode) {
		this.bankTypeCode = bankTypeCode;
	}
	public String getbankTypeDetails() {
		return bankTypeDetails;
	}
	public void setbankTypeDetails(String bankTypeDetails) {
		this.bankTypeDetails = bankTypeDetails;
	}	
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getBankTypeDetails() {
		return bankTypeDetails;
	}
	public void setBankTypeDetails(String bankTypeDetails) {
		this.bankTypeDetails = bankTypeDetails;
	}
	public String getLevelFlag() {
		return levelFlag;
	}
	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
}
