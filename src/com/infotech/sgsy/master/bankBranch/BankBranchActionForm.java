package com.infotech.sgsy.master.bankBranch;

import java.io.Serializable;

import com.infotech.sgsy.common.MasterForm;

public class BankBranchActionForm extends MasterForm implements Serializable {

	private String entityCode;
	private String bankBranchCode;
	private String bankCode;
	private String bankBranchName;
	private String ifscCode;
	private String ifscCodeOld;
	private String activeFlag;
	private String address;
	private String bankTypeCode;
	private String villageCode;
	private String flag;
	private String checkBankBranchCodes;
	private String[] blockCodes;
	private String isBlock;
	private String branchLevel;
	private String bankLevel;

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
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

	public String getIfscCodeOld() {
		return ifscCodeOld;
	}

	public void setIfscCodeOld(String ifscCodeOld) {
		this.ifscCodeOld = ifscCodeOld;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankTypeCode() {
		return bankTypeCode;
	}

	public void setBankTypeCode(String bankTypeCode) {
		this.bankTypeCode = bankTypeCode;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCheckBankBranchCodes() {
		return checkBankBranchCodes;
	}

	public void setCheckBankBranchCodes(String checkBankBranchCodes) {
		this.checkBankBranchCodes = checkBankBranchCodes;
	}
	public String[] getBlockCodes() {
		return blockCodes;
	}
	public void setBlockCodes(String[] blockCodes) {
		this.blockCodes = blockCodes;
	}
	public String getIsBlock() {
		return isBlock;
	}
	public void setIsBlock(String isBlock) {
		this.isBlock = isBlock;
	}
	public String getBranchLevel() {
		return branchLevel;
	}
	public void setBranchLevel(String branchLevel) {
		this.branchLevel = branchLevel;
	}
	public String getBankLevel() {
		return bankLevel;
	}
	public void setBankLevel(String bankLevel) {
		this.bankLevel = bankLevel;
	}

	public void reset() {
		super.reset();
		bankBranchName = "";
		bankCode = "";
		ifscCode = "";
		activeFlag = "";
		address = "";
		entityCode = "";
		bankBranchCode = "";
		bankTypeCode = "";
		villageCode = "";
		flag = "";
		checkBankBranchCodes = "";
		isBlock = "";
	}

	
	
	
}
