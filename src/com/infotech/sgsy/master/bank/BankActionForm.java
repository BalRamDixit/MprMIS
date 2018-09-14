 package com.infotech.sgsy.master.bank;
 
import java.io.Serializable;
import java.util.List;

import com.infotech.sgsy.common.MasterForm;
import com.infotech.sgsy.common.MasterVO;

public class BankActionForm extends MasterForm implements Serializable {
	
 	
	private String bankCode;
	private String bankName;
	private String bankTypeCode;
	private String bankShortName;
	private String activeFlag;
	private String bankLevelCode ;
	private String bankTypeDetail;
	private String banklLevelName;
	private String bankLevelName;
	private String checkBankCodes;
	private String levelCode;
	private String blockCode;
	public String getLevelCode() {
		return levelCode;
	}


	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}


	public String getBlockCode() {
		return blockCode;
	}


	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}


	
	public String getBankLevelName() {
		return bankLevelName;
	}


	public String getCheckBankCodes() {
		return checkBankCodes;
	}


	public void setCheckBankCodes(String checkBankCodes) {
		this.checkBankCodes = checkBankCodes;
	}


	public void setBankLevelName(String bankLevelName) {
		this.bankLevelName = bankLevelName;
	}


	public String getBankTypeDetail() {
		return bankTypeDetail;
	}


	public void setBankTypeDetail(String bankTypeDetail) {
		this.bankTypeDetail = bankTypeDetail;
	}


	public String getBanklLevelName() {
		return banklLevelName;
	}


	public void setBanklLevelName(String banklLevelName) {
		this.banklLevelName = banklLevelName;
	}

	public void reset() {	
		super.reset();		
		bankName = "";
		bankTypeCode ="";
		bankShortName ="" ;
		activeFlag ="";
		bankLevelCode ="" ;
		bankTypeDetail="";
		banklLevelName="";
		bankCode="";
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
	public String getBankShortName() {
		return bankShortName;
	}
	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getbankTypeDetail() {
		return bankTypeDetail;
	}


	public void setbankTypeDetail(String bankLevelCode) {
		this.bankTypeDetail = bankLevelCode;
	}
	public String getbanklLevelName() {
		return banklLevelName;
	}


	public void setbanklLevelName(String bankLevelCode) {
		this.banklLevelName = bankLevelCode;
	}
}
