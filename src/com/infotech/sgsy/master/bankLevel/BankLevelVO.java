package com.infotech.sgsy.master.bankLevel;
 
import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;

public  class BankLevelVO extends MasterVO implements Serializable {
	
	
	private String bankLevelCode;
	private String bankLevelName;
	private String activeFlag;
	
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getBankLevelCode() {
		return bankLevelCode;
	}
	public void setBankLevelCode(String bankLevelCode) {
		this.bankLevelCode = bankLevelCode;
	}
	public String getBankLevelName() {
		return bankLevelName;
	}
	public void setBankLevelName(String bankLevelName) {
		this.bankLevelName = bankLevelName;
	}
	
	

}
