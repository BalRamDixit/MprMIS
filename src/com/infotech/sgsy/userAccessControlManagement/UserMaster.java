package com.infotech.sgsy.userAccessControlManagement;

import java.io.Serializable;
import java.util.Date;

import com.infotech.sgsy.master.ctsaMaster.CtsaMasterVO;
import com.infotech.sgsy.master.state.StateVO;

public class UserMaster implements Serializable {
	
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
	private String roleId;
	private StateVO stateId;
	private CtsaMasterVO ctsaId;
	
	public StateVO getStateId() {
		return stateId;
	}
	public void setStateId(StateVO stateId) {
		this.stateId = stateId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	@Override
	public String toString() {
		return "UserMaster [createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", id=" + id + ", userName=" + userName + ", loginId=" + loginId
				+ ", password=" + password + ", emailId=" + emailId + ", loginStatus=" + loginStatus + ", loginAttempt="
				+ loginAttempt + ", mobileNo=" + mobileNo + ", activeFlag=" + activeFlag + ", roleId=" + roleId
				+ ", stateId=" + stateId + "]";
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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public CtsaMasterVO getCtsaId() {
		return ctsaId;
	}
	public void setCtsaId(CtsaMasterVO ctsaId) {
		this.ctsaId = ctsaId;
	}
	

}
