package com.infotech.sgsy.accessControl;

 

import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;

public class RoleMasterVO extends MasterVO implements Serializable{
	
	private String modcd;
	private String modName;
	private String formcd;
	private String formName;
	private String moduleStatus;
	private boolean permission;
	private String formURL;
	private String roleId;
	private String roleName;
	private String roleDescription;
	private String levelRoleName;
	private String schemeCode;
	private String schemeName;
	
	public String getLevelRoleName() {
		return levelRoleName;
	}
	public void setLevelRoleName(String levelRoleName) {
		this.levelRoleName = levelRoleName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getModcd() {
		return modcd;
	}
	public void setModcd(String modcd) {
		this.modcd = modcd;
	}
	public String getModName() {
		return modName;
	}
	public void setModName(String modName) {
		this.modName = modName;
	}
	public String getFormcd() {
		return formcd;
	}
	public void setFormcd(String formcd) {
		this.formcd = formcd;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getModuleStatus() {
		return moduleStatus;
	}
	public void setModuleStatus(String moduleStatus) {
		this.moduleStatus = moduleStatus;
	}
	public boolean isPermission() {
		return permission;
	}
	public void setPermission(boolean permission) {
		this.permission = permission;
	}
	public String getFormURL() {
		return formURL;
	}
	public void setFormURL(String formURL) {
		this.formURL = formURL;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	

}
