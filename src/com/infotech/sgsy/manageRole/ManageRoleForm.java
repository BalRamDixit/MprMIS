package com.infotech.sgsy.manageRole;

import com.infotech.sgsy.common.MasterForm;

public class ManageRoleForm extends MasterForm{
	private String levelCode;
	private String entityCode;
	private String userCode;	
	
	private String blockCode;
	private String villageCode;
	private String roleCode;
	private String levelRoleCode;
	private String loginId;
	private String roleName;
	private String roleDesc;
	
	private String stateName;
	private String districtName;
	private String blockName;
	private String villageName;
	private String levelCodeName;
	private String levelCodeAName;
	
	private String hiddenStateCode;
	private String hiddenDistrictCode;
	private String hiddenBlockCode;
	private String hiddenVillageCode;
	private String hiddenLoginId;
	private String hiddenLevelCode;
	
	private String stateCodeRole;
	private String districtCodeRole;
	private String blockCodeRole;
	private String selectedStateM;
	private String selectedDistrictM;
	private String selectedBlockM;
	private String selectedVillageM;
	
	
	private String villageCodes;
	private String selectedVillageCode = null;	
	
	private String blockCodes;
	private String selectedBlockCode = null;
	
	
	private String districtCodes;
	private String selectedDistrictCode = null;
	
	
	private String stateCodes;
	
	private String selectedStateCode = null;
		
	
	private String levelCodeA = null;
	

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
	
	public void reset() {
		super.reset();		
		levelCode = null;
		entityCode = null;				

		blockCode = null;
		villageCode = null;	
		userCode = null;		
		blockCodes = null;
		villageCodes = null;
		districtCodes = null;
		stateCodes = null;
		stateCodeRole = null;
		districtCodeRole = null;
		blockCodeRole = null;		
		levelCodeA = null;
		levelRoleCode = null;
		loginId = null;
		roleName = null;
		roleDesc = null;
		
		hiddenStateCode = null;
		hiddenDistrictCode = null;
		hiddenBlockCode = null; 
		hiddenVillageCode = null;
		hiddenLoginId = null;
		hiddenLevelCode = null;
		
		stateName = null;
		districtName = null;
		blockName = null;
		villageName = null;
		levelCodeName = null;
		levelCodeAName = null;
		selectedVillageM = null;
		selectedBlockM = null;
		selectedDistrictM = null;
		selectedStateM = null;
		
	}
	
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getSelectedVillageCode() {
		return selectedVillageCode;
	}
	public void setSelectedVillageCode(String selectedVillageCode) {
		this.selectedVillageCode = selectedVillageCode;
	}
	
	public String getVillageCodes() {
		return villageCodes;
	}
	public void setVillageCodeRole(String villageCodes) {
		this.villageCode = villageCodes;
	}
	public String getBlockCodes() {
		return blockCodes;
	}
	public void setBlockCodes(String blockCodes) {
		this.blockCodes = blockCodes;
	}
	public String getSelectedBlockCode() {
		return selectedBlockCode;
	}
	public void setSelectedBlockCode(String selectedBlockCode) {
		this.selectedBlockCode = selectedBlockCode;
	}
	
	
	public void setVillageCodes(String villageCodes) {
		this.villageCodes = villageCodes;
	}
	public String getDistrictCodes() {
		return districtCodes;
	}
	public void setDistrictCodes(String districtCodes) {
		this.districtCodes = districtCodes;
	}
	public String getSelectedDistrictCode() {
		return selectedDistrictCode;
	}
	public void setSelectedDistrictCode(String selectedDistrictCode) {
		this.selectedDistrictCode = selectedDistrictCode;
	}
	
	public String getStateCodes() {
		return stateCodes;
	}
	public void setStateCodes(String stateCodes) {
		this.stateCodes = stateCodes;
	}
	public String getSelectedStateCode() {
		return selectedStateCode;
	}
	public void setSelectedStateCode(String selectedStateCode) {
		this.selectedStateCode = selectedStateCode;
	}
	
	
	public String getStateCodeRole() {
		return stateCodeRole;
	}
	public void setStateCodeRole(String stateCodeRole) {
		this.stateCodeRole = stateCodeRole;
	}
	public String getDistrictCodeRole() {
		return districtCodeRole;
	}
	public void setDistrictCodeRole(String districtCodeRole) {
		this.districtCodeRole = districtCodeRole;
	}
	public String getBlockCodeRole() {
		return blockCodeRole;
	}
	public void setBlockCodeRole(String blockCodeRole) {
		this.blockCodeRole = blockCodeRole;
	}
	
	public String getLevelCodeA() {
		return levelCodeA;
	}
	public void setLevelCodeA(String levelCodeA) {
		this.levelCodeA = levelCodeA;
	}
	public String getLevelRoleCode() {
		return levelRoleCode;
	}
	public void setLevelRoleCode(String levelRoleCode) {
		this.levelRoleCode = levelRoleCode;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getLevelCodeName() {
		return levelCodeName;
	}
	public void setLevelCodeName(String levelCodeName) {
		this.levelCodeName = levelCodeName;
	}
	public String getLevelCodeAName() {
		return levelCodeAName;
	}
	public void setLevelCodeAName(String levelCodeAName) {
		this.levelCodeAName = levelCodeAName;
	}
	public String getSelectedStateM() {
		return selectedStateM;
	}
	public void setSelectedStateM(String selectedStateM) {
		this.selectedStateM = selectedStateM;
	}
	public String getSelectedDistrictM() {
		return selectedDistrictM;
	}
	public void setSelectedDistrictM(String selectedDistrictM) {
		this.selectedDistrictM = selectedDistrictM;
	}
	public String getSelectedBlockM() {
		return selectedBlockM;
	}
	public void setSelectedBlockM(String selectedBlockM) {
		this.selectedBlockM = selectedBlockM;
	}
	public String getSelectedVillageM() {
		return selectedVillageM;
	}
	public void setSelectedVillageM(String selectedVillageM) {
		this.selectedVillageM = selectedVillageM;
	}
	
}