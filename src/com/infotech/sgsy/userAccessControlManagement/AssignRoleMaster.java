package com.infotech.sgsy.userAccessControlManagement;

import java.util.Date;

public class AssignRoleMaster {
	
	
	
	@Override
	public String toString() {
		return "AssignRoleMaster [createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", id=" + id + ", roleId=" + roleId + ", userId=" + userId
				+ ", formModuleId=" + formModuleId + ", permissionChar=" + permissionChar + "]";
	}
	public String getCreatedBy() {
		return createdBy;
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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFormModuleId() {
		return formModuleId;
	}
	public void setFormModuleId(String formModuleId) {
		this.formModuleId = formModuleId;
	}
	public String getPermissionChar() {
		return permissionChar;
	}
	public void setPermissionChar(String permissionChar) {
		this.permissionChar = permissionChar;
	}
	
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String id;
	private String roleId;
	private String userId;
	private String formModuleId;
	private String permissionChar;

}
