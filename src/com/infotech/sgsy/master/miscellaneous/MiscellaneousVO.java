package com.infotech.sgsy.master.miscellaneous;

import com.infotech.sgsy.common.MasterVO;

public class MiscellaneousVO extends MasterVO{

	private String objectCode;
	private String objectName;
	private String critera;
	private String flag;
	
	public String getObjectCode() {
		return objectCode;
	}
	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getCritera() {
		return critera;
	}
	public void setCritera(String critera) {
		this.critera = critera;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	};
	
}
