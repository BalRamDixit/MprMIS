package com.infotech.sgsy.userManagement;

import com.infotech.sgsy.common.MasterVO;

public class UserVO extends MasterVO{
	private String levelCode;
	private String entityCode;
	private String userCode;
	private String userName;
	private String userDesignation;
	private String loginId;
	private String emailId;
	private String activeFlag;
	private String password;
	private String mysaCode;
	private String stateCode;
	private String districtCode;
	private String villageCode;
	private String blockCode;
	private String roleCode;
	private String entityName;
	
	private String hiddenStateCode;
	private String hiddenDistrictCode;
	private String hiddenBlockCode;
	private String hiddenVillageCode;
	private String hiddenLoginId;
	private String hiddenLevelCode;
	private String newpassword;
	private String confirmpassword;
	private String mobile;
	
	private String stateName;
	private String districtName;
	private String blockName;
	
	private String noOfUsers;
	
	
	public String getNoOfUsers() {
		return noOfUsers;
	}

	public void setNoOfUsers(String noOfUsers) {
		this.noOfUsers = noOfUsers;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public UserVO() {
		super();
	}

	public UserVO(String levelCode,String entityCode,String userCode,String userName,
			String userDesignation,	String loginId,String emailId,String activeFlag,String password
			,String mysaCode,String stateCode,String districtCode,String blockCode,String villageCode,String mobile) {
		super();
		this.levelCode = levelCode;
		this.entityCode = entityCode;
		this.userCode = userCode;
		this.userName = userName;
		this.userDesignation = userDesignation;
		this.loginId = loginId;
		this.emailId = emailId;
		this.activeFlag = activeFlag;
		this.password = password;
		this.mysaCode = mysaCode;
		this.stateCode = stateCode;
		this.districtCode = districtCode;
		this.blockCode = blockCode;
		this.villageCode = villageCode;
		this.mobile = mobile;
		
	}
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDesignation() {
		return userDesignation;
	}
	public void setUserDesignation(String userDesignation) {
		this.userDesignation = userDesignation;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getMysaCode() {
		return mysaCode;
	}

	public void setMysaCode(String mysaCode) {
		this.mysaCode = mysaCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getHiddenStateCode() {
		return hiddenStateCode;
	}

	public void setHiddenStateCode(String hiddenStateCode) {
		this.hiddenStateCode = hiddenStateCode;
	}

	public String getHiddenDistrictCode() {
		return hiddenDistrictCode;
	}

	public void setHiddenDistrictCode(String hiddenDistrictCode) {
		this.hiddenDistrictCode = hiddenDistrictCode;
	}

	public String getHiddenBlockCode() {
		return hiddenBlockCode;
	}

	public void setHiddenBlockCode(String hiddenBlockCode) {
		this.hiddenBlockCode = hiddenBlockCode;
	}

	public String getHiddenVillageCode() {
		return hiddenVillageCode;
	}

	public void setHiddenVillageCode(String hiddenVillageCode) {
		this.hiddenVillageCode = hiddenVillageCode;
	}

	public String getHiddenLoginId() {
		return hiddenLoginId;
	}

	public void setHiddenLoginId(String hiddenLoginId) {
		this.hiddenLoginId = hiddenLoginId;
	}

	public String getHiddenLevelCode() {
		return hiddenLevelCode;
	}

	public void setHiddenLevelCode(String hiddenLevelCode) {
		this.hiddenLevelCode = hiddenLevelCode;
	}
	
}