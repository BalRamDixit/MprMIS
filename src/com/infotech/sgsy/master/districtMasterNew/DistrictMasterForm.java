package com.infotech.sgsy.master.districtMasterNew;

import com.infotech.sgsy.common.MasterForm;

public class DistrictMasterForm extends MasterForm {
	
	private String districtId;
	private String districtCode;
	private String districtName;
	private String specialArea;
	private String state_id;
	private String typeOfSpecialArea;
	
	
	
	
	public String getState_id() {
		return state_id;
	}
	public void setState_id(String state_id) {
		this.state_id = state_id;
	}
	public String getTypeOfSpecialArea() {
		return typeOfSpecialArea;
	}
	public void setTypeOfSpecialArea(String typeOfSpecialArea) {
		this.typeOfSpecialArea = typeOfSpecialArea;
	}
	/*private String typeOfSpecialArea;*/
	//private StateVO state=new StateVO();
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getSpecialArea() {
		return specialArea;
	}
	public void setSpecialArea(String specialArea) {
		this.specialArea = specialArea;
	}
	/*public String getTypeOfSpecialArea() {
		return typeOfSpecialArea;
	}
	public void setTypeOfSpecialArea(String typeOfSpecialArea) {
		this.typeOfSpecialArea = typeOfSpecialArea;
	}*/
	 
	
	
}
