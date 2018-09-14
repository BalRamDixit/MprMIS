package com.infotech.sgsy.master.districtMasterNew;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.master.specialArea.SpecialAreaVo;
import com.infotech.sgsy.master.state.StateVO;

public class DistrictMasterVO  implements Serializable {
	
	private String districtId;
	private String districtCode;
	private String districtName;
	private String specialArea;
	private StateVO state;
	private SpecialAreaVo typeSpecialArea;
	private Date createdOn;
	private String createdBy;
	private String updateBy;
	private Date updatedOn;
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
	public StateVO getState() {
		return state;
	}
	
	public SpecialAreaVo getTypeSpecialArea() {
		return typeSpecialArea;
	}
	public void setTypeSpecialArea(SpecialAreaVo typeSpecialArea) {
		this.typeSpecialArea = typeSpecialArea;
	}
	public void setState(StateVO state) {
		this.state = state;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	

}
