package com.infotech.sgsy.userAccessControlManagement;

import java.util.Date;

public class ProjectMapping {
	
	public ProjectMapping() {
		super();
	}
	public ProjectMapping(String projectId, String userId) {
		super();
		this.projectId = projectId;
		this.userId = userId;
	}
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String id;
	private String projectId;
	private String userId;
	public String getCreatedBy() {
		return createdBy;
	}
	@Override
	public String toString() {
		return "ProjectMapping [createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", id=" + id + ", projectId=" + projectId + ", userId=" + userId
				+ "]";
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
