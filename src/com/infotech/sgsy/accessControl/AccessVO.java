package com.infotech.sgsy.accessControl;

import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;

public class AccessVO extends MasterVO implements Serializable{
	
	private int id;
	private String roleId;
	private String roleName;
	private String roleDescription;
	private String levelRoleName;
	private String levelRoleCode;
	private String levelCode;
	private String schemeCode;
		
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getLevelRoleCode() {
		return levelRoleCode;
	}
	public void setLevelRoleCode(String levelRoleCode) {
		this.levelRoleCode = levelRoleCode;
	}
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	
}
