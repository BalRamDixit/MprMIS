package com.infotech.sgsy.userAccessControlManagement;

import java.util.Arrays;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class AssignRoleMasterBean  extends ValidatorForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5523547166145851429L;
	@Override
	public String toString() {
		return "AssignRoleMasterBean [createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", id=" + id + ", roleId=" + roleId + ", userId="
				+ userId + ", formModuleIdList=" + Arrays.toString(formModuleIdList) + ", permissionCharList="
				+ Arrays.toString(permissionCharList) + ", formModuleId=" + formModuleId + ", permissionChar="
				+ permissionChar + ", editId=" + editId + ", accessModuleIdList=" + Arrays.toString(accessModuleIdList)
				+ "]";
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public String[] getFormModuleIdList() {
		return formModuleIdList;
	}
	public void setFormModuleIdList(String[] formModuleIdList) {
		this.formModuleIdList = formModuleIdList;
	}
	public String[] getPermissionCharList() {
		return permissionCharList;
	}
	public void setPermissionCharList(String[] permissionCharList) {
		this.permissionCharList = permissionCharList;
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
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public AssignRoleMasterBean() {
		super();
	}
	
	public AssignRoleMasterBean(String createdBy, String updatedBy, String roleId, String userId, String editId) {
		super();
		if( createdBy.equalsIgnoreCase("null")){
			this.createdBy ="";
		}
		else{
			this.createdBy = createdBy;
		}
		if( updatedBy.equalsIgnoreCase("null")){
			this.updatedBy = "";
		}
		else{
			this.updatedBy = updatedBy;
		}
		if( roleId.equalsIgnoreCase("null")){
			this.roleId = "";
		}
		else{
			this.roleId = roleId;
		}
		if( userId.equalsIgnoreCase("null")){
			this.userId = "";
		}
		else{
			this.userId = userId;
		}
		if( editId.equalsIgnoreCase("null")){
			this.editId = "";
		}
		else{
			this.editId = editId;
		}
		
		System.out.println("====> "+userId+"------"+editId);
		if(userId.equalsIgnoreCase("RoleNull")){
			this.id=editId;
		}
		else{
			this.id=userId;
		}
		
	}
	public AssignRoleMasterBean(String roleId, String userId, String formModuleId, String permissionChar) {
		super();
		this.roleId = roleId;
		this.userId = userId;
		this.formModuleId = formModuleId;
		this.permissionChar = permissionChar;
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
	
	public String getEditId() {
		return editId;
	}
	public void setEditId(String editId) {
		this.editId = editId;
	}
	public String[] getAccessModuleIdList() {
		return accessModuleIdList;
	}
	public void setAccessModuleIdList(String[] accessModuleIdList) {
		this.accessModuleIdList = accessModuleIdList;
	}
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String id;
	private String roleId;
	private String userId;
	private String[] formModuleIdList;
	private String[] permissionCharList;
	private String formModuleId;
	private String permissionChar;
	private String editId;
	private String[] accessModuleIdList;

}
