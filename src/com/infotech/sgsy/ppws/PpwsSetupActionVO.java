package com.infotech.sgsy.ppws;

import java.util.Date;

import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;

public class PpwsSetupActionVO {

	private String id;
//	public String projectId;
	private ProjectDetailsVO project;
	private String entryMonth;
	private String entryYear;
	private String expTrainComn;
	private String expTrainComp;
	private String placeExp;
	
	
	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	  
	public ProjectDetailsVO getProject() {
		return project;
	}
	public void setProject(ProjectDetailsVO project) {
		this.project = project;
	}
	public String getEntryMonth() {
		return entryMonth;
	}
	public void setEntryMonth(String entryMonth) {
		this.entryMonth = entryMonth;
	}
	public String getEntryYear() {
		return entryYear;
	}
	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExpTrainComn() {
		return expTrainComn;
	}
	public void setExpTrainComn(String expTrainComn) {
		this.expTrainComn = expTrainComn;
	}
	public String getExpTrainComp() {
		return expTrainComp;
	}
	public void setExpTrainComp(String expTrainComp) {
		this.expTrainComp = expTrainComp;
	}
	public String getPlaceExp() {
		return placeExp;
	}
	public void setPlaceExp(String placeExp) {
		this.placeExp = placeExp;
	}
	/*public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}*/
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
}
