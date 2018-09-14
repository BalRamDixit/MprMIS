package com.infotech.sgsy.manageRole;

import com.infotech.sgsy.common.MasterVO;

public class ManageRoleVO extends MasterVO{
	private String levelCode;
	private String entityCode;
	private String userCode;	
	private String stateCode;
	private String districtCode;
	private String blockCode;
	private String villageCode;
	private String roleCode;
	private String selectedVillageCode = null;
	private String delimetedVillageCode = null;	

	private String levelCodeA;
	private String loginId;
	private String roleName;
	private String roleDesc;
	
	private String stateName;
	private String districtName;
	private String blockName;
	private String villageName;
	private String levelCodeName;
	private String levelCodeAName;
	
	private String selectedStateM;
	private String selectedDistrictM;
	private String selectedBlockM;
	private String selectedVillageM;

	public ManageRoleVO() {
		super();
	}

	public ManageRoleVO(String levelCode,String entityCode,String userCode,String stateCode,String districtCode,
			String blockCode,String villageCode,String roleCode,String selectedVillageCode,
			String delimetedVillageCode,String levelCodeA,String loginId,String roleName,String roleDesc
			,String stateName,String districtName,String blockName,String villageName,String levelCodeName
			,String levelCodeAName) {
		super();
		this.levelCode = levelCode;
		this.entityCode = entityCode;
		this.userCode = userCode;		
		this.stateCode = stateCode;
		this.districtCode = districtCode;
		this.blockCode = blockCode;
		this.villageCode = villageCode;
		this.roleCode = roleCode;
		this.selectedVillageCode = selectedVillageCode;
		this.delimetedVillageCode = delimetedVillageCode;
		this.levelCodeA = levelCodeA;
		this.loginId = loginId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.stateName = stateName;
		this.districtName = districtName;
		this.blockName = blockName;
		this.villageName = villageName;
		this.levelCodeName = levelCodeName;
		this.levelCodeAName = levelCodeAName;;
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

	public String getSelectedVillageCode() {
		return selectedVillageCode;
	}

	public void setSelectedVillageCode(String selectedVillageCode) {
		this.selectedVillageCode = selectedVillageCode;
	}

	public String getDelimetedVillageCode() {
		return delimetedVillageCode;
	}

	public void setDelimetedVillageCode(String delimetedVillageCode) {
		this.delimetedVillageCode = delimetedVillageCode;
	}

	public String getLevelCodeA() {
		return levelCodeA;
	}

	public void setLevelCodeA(String levelCodeA) {
		this.levelCodeA = levelCodeA;
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