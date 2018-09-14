package com.infotech.sgsy.master.designationMaster;

import org.apache.struts.action.ActionForm;

import com.infotech.sgsy.common.MasterForm;

public class DesignationMasterForm extends ActionForm{
	private String designationId;
	private String designationCode;
	private String designationName;
	
	
	public String getDesignationId() {
		return designationId;
	}
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	
	
}
