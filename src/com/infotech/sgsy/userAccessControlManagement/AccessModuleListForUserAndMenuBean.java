package com.infotech.sgsy.userAccessControlManagement;

import java.util.List;
import java.util.Map;

public class AccessModuleListForUserAndMenuBean {

	private String userName;
	private String emailId;
	private String roleName;
	private String roleDesc;
	private Map<String,Map<String,FormModuleMenuBean>> moduleList;
	private String roleId;
	private String userId;
	private String userRoleId;
	private String stateId;

   
	@Override
	public String toString() {
		return "AccessModuleListForUserAndMenuBean [userName=" + userName + ", emailId=" + emailId + ", roleName="
				+ roleName + ", roleDesc=" + roleDesc + ", moduleList=" + moduleList + ", roleId=" + roleId + ", userId="
				+ userId + ", userRoleId=" + userRoleId + ", stateId=" + stateId + "]";
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	

	

	public Map<String, Map<String, FormModuleMenuBean>> getModuleList() {
		return moduleList;
	}

	public void setModuleList(Map<String, Map<String, FormModuleMenuBean>> moduleList) {
		this.moduleList = moduleList;
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

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	
}
