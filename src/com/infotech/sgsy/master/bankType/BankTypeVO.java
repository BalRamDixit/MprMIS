package com.infotech.sgsy.master.bankType;
 
import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;

public class BankTypeVO extends MasterVO implements Serializable {
	
	
	private String bankTypeCode;
	private String bankTypeDetail;
	private String activeFlag;
	public String getBankTypeCode() {
		return bankTypeCode;
	}
	public void setBankTypeCode(String bankTypeCode) {
		this.bankTypeCode = bankTypeCode;
	}
	public String getBankTypeDetail() {
		return bankTypeDetail;
	}
	public void setBankTypeDetail(String bankTypeDetail) {
		this.bankTypeDetail = bankTypeDetail;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	 
	
	
	

}
