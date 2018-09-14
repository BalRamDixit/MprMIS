package com.infotech.sgsy.userAccessControlManagement;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class UserMasterBean extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7764979832061613336L;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String id;
	private String userName;
	private String loginId;
	private String password;
	private String emailId;
	private String loginStatus;
	private Integer loginAttempt;
	private String mobileNo;
	private String activeFlag;
	private String editId;
	private String roleId;
	private String state;
	private String ctsa;
	
	
	public String getCreatedBy() {
		return createdBy;
	}
	@Override
	public String toString() {
		return "UserMasterBean [createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", id=" + id + ", userName=" + userName + ", loginId=" + loginId
				+ ", password=" + password + ", emailId=" + emailId + ", loginStatus=" + loginStatus + ", loginAttempt="
				+ loginAttempt + ", mobileNo=" + mobileNo + ", activeFlag=" + activeFlag + ", editId=" + editId + "]";
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Integer getLoginAttempt() {
		return loginAttempt;
	}
	public void setLoginAttempt(Integer loginAttempt) {
		this.loginAttempt = loginAttempt;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getEditId() {
		return editId;
	}
	public void setEditId(String editId) {
		this.editId = editId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
		String s=new String();
		
	}
	public String getCtsa() {
		return ctsa;
	}
	public void setCtsa(String ctsa) {
		this.ctsa = ctsa;
	}
	
	

}
