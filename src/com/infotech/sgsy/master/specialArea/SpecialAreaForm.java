package com.infotech.sgsy.master.specialArea;

import org.apache.struts.action.ActionForm;

import com.infotech.sgsy.common.MasterForm;

public class SpecialAreaForm extends ActionForm{

	
	private String specialAreaId;
	private String specialAreaCode;
	private String specialAreaName;
	
	public String getSpecialAreaId() {
		return specialAreaId;
	}
	public void setSpecialAreaId(String specialAreaId) {
		this.specialAreaId = specialAreaId;
	}
	public String getSpecialAreaCode() {
		return specialAreaCode;
	}
	public void setSpecialAreaCode(String specialAreaCode) {
		this.specialAreaCode = specialAreaCode;
	}
	public String getSpecialAreaName() {
		return specialAreaName;
	}
	public void setSpecialAreaName(String specialAreaName) {
		this.specialAreaName = specialAreaName;
	}
	
	
}


