package com.infotech.sgsy.projectSetup.districtTarget;

import com.infotech.sgsy.common.MasterForm;



public class DistrictTargetActionForm extends MasterForm {

	private String[] id;
	private String projectId;  

	private String stateName;
	private String state_Code;
	
	public String getState_Code() {
		return state_Code;
	}
	public void setState_Code(String state_Code) {
		this.state_Code = state_Code;
	}
	private String entityCode;
	
	private int projectTarget;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	} 	
	public int getProjectTarget() {
		return projectTarget;
	}
	public void setProjectTarget(int projectTarget) {
		this.projectTarget = projectTarget;
	}
	private String[] districtName;
	private String[] district;
	private String[] isSpecial;
	public String[] getIsSpecial() {
		return isSpecial;
	}
	private Integer[] trainingTargetDist;
	
	private String createdOnDate;
	private String updatedOnDate;
	
	
	
	
	public String[] getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String[] districtName) {
		this.districtName = districtName;
	}
	
	public void setDistrict(String[] district) {
		this.district = district;
	}
	public void setIsSpecial(String[] isSpecial) {
		this.isSpecial = isSpecial;
	}
	public void setTrainingTargetDist(Integer[] trainingTargetDist) {
		this.trainingTargetDist = trainingTargetDist;
	}
	public String[] getDistrict() {
		return district;
	}
	public Integer[] getTrainingTargetDist() {
		return trainingTargetDist;
	}
	
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}

	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	
	public String getCreatedOnDate() {
		return createdOnDate;
	}
	public void setCreatedOnDate(String createdOnDate) {
		this.createdOnDate = createdOnDate;
	}
	public String getUpdatedOnDate() {
		return updatedOnDate;
	}
	public void setUpdatedOnDate(String updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}
	
}
