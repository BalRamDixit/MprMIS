package com.infotech.sgsy.master.specialArea;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;



public class SpecialAreaVo extends MasterVO implements Serializable{

	private String specialAreaId;
	private String specialAreaCode;
	private String specialAreaName;
	/*private Set<DistrictMasterVO> district=new HashSet<DistrictMasterVO>(0);*/
	
	
	
	private Date createdOn;
	private String createdBy;
	private String updateBy;
	private Date updatedOn;
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
	/*public Set<DistrictMasterVO> getDistrict() {
		return district;
	}
	public void setDistrict(Set<DistrictMasterVO> district) {
		this.district = district;
	}*/
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
