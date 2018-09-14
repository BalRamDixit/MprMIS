package com.infotech.sgsy.projectSetup.districtTarget;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;

public class DistrictTargetVO implements Serializable {
	
	 @Override
	public String toString() {
		return "DistrictTargetVO [id=" + id + ", projectId=" + projectId + ", entityCode=" + entityCode + ", district="
				+ district + ", trainingTargetDist=" + trainingTargetDist + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", createdOnDate=" + createdOnDate + ", updatedOnDate=" + updatedOnDate + "]";
	}
	private String id;
	/*private String projectID;  */
	 
    private ProjectDetailsVO projectId;
	private String entityCode;
	
	private String district;

	private Integer trainingTargetDist;
	
	private String createdBy;
	private String updatedBy;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private Date createdOnDate;
	private Date updatedOnDate;
	
	


	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getTrainingTargetDist() {
		return trainingTargetDist;
	}
	public void setTrainingTargetDist(Integer trainingTargetDist) {
		this.trainingTargetDist = trainingTargetDist;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedOnDate() {
		return createdOnDate;
	}
	
	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}
	
	public Date getUpdatedOnDate() {
		return updatedOnDate;
	}
	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}
	public ProjectDetailsVO getProjectId() {
		return projectId;
	}
	public void setProjectId(ProjectDetailsVO projectId) {
		this.projectId = projectId;
	}
	
	
}
