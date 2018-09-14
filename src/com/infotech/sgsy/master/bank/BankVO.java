 package com.infotech.sgsy.master.bank;
 
import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;

public class BankVO extends MasterVO implements Serializable {
	
	
	private String bankCode;
	private String bankName;
	private String bankTypeCode;
	private String bankShortName;
	private String activeFlag;
	private String bankLevelCode ;
	private String bankTypeDetails;
	private String bankLevelName;
	private String entityCode;
	
	 
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

	public String getbankTypeDetails() {
		return bankTypeDetails;
	}

	public void setbankTypeDetails(String bankTypeDetails) {
		this.bankTypeDetails = bankTypeDetails;
	}
	public String getbankLevelName() {
		return bankLevelName;
	}

	public void setbanklLevelName(String bankLevelName) {
		this.bankLevelName = bankLevelName;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	

}
