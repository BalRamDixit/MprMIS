package com.infotech.sgsy.userAccessControlManagement;

import java.util.Arrays;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class ProjectMappingBean  extends ValidatorForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6034165715779979227L;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String id;
	private String tempId;
	private String pid;
	
	public ProjectMappingBean( String userId,String editId,String userIdId) {
		super();
		
		this.projectId = userIdId;
		this.userId = userId;
		this.editId = editId;
	}
	public ProjectMappingBean() {
		
	}
	private String projectId;
	private String userId;
	private String editId;
	private String[] selectedProjectId;
	private String[] selectedUserId;
	public String getCreatedBy() {
		return createdBy;
	}
	@Override
	public String toString() {
		return "ProjectMappingBean [createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", id=" + id + ", projectId=" + projectId + ", userId="
				+ userId + ", editId=" + editId + ", selectedProjectId=" + Arrays.toString(selectedProjectId)
				+ ", selectedUserId=" + Arrays.toString(selectedUserId) + "]";
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
	public String getEditId() {
		return editId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String[] getSelectedProjectId() {
		return selectedProjectId;
	}
	public void setSelectedProjectId(String[] selectedProjectId) {
		this.selectedProjectId = selectedProjectId;
	}
	public String[] getSelectedUserId() {
		return selectedUserId;
	}
	public void setSelectedUserId(String[] selectedUserId) {
		this.selectedUserId = selectedUserId;
	}
	public void setEditId(String editId) {
		this.editId = editId;
	}
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

}
