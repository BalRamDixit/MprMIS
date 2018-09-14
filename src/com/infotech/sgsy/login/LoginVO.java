package com.infotech.sgsy.login;

import java.io.Serializable;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.master.state.StateDaoImp;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.userAccessControlManagement.RoleMaster;
import com.infotech.sgsy.userAccessControlManagement.RoleMasterDaoImpl;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;

public class LoginVO extends MasterVO implements Serializable {
	
	private String userid;
	private String password;
	private String userName;
	private String userDesignation;
	private String entityCode;
	private String levelCode;
	private String statusFlag;
	private String entityName;
	private String entityType;
	private String entityCodeLevel;
	
	private String createdBy;
	private String creationOn;
	private String modifiedBy;
	private String modifiedOn;
	
	private String panchayatRole;
	private String districtPanchayat;
	private String blockPanchayat;
	private String villagePanchayat;
	
	private String stateCode;
	private String stateName;
	private String districtCode;
	private String districtName;
	private String blockCode;
	private String blockName;
	private String villageCode;
	private String villageName;
	
	private String OldEntityCode;
	private String OldEntityName;
	private String OldEntityType;
	private String logged_in;
	
	private String ipAddress;
	
	private int loginAttempt;
	private boolean locationMasterCompleted;

	private String blockPanch;
	private String selPage="selPage";
	
	private int count;
	private String roleLevel;
	private String roleId;
	private String roleName;
	
	public LoginVO() {
		super();
	}
	public LoginVO(UserMaster um) {
		super();
		System.out.println("User master==> "+um);
		this.userid=um.getId();
		this.userName=um.getLoginId();
		this.userDesignation=new RoleMasterDaoImpl().getRecordFromId(um.getRoleId()).getRoleName();
		this.roleId=um.getRoleId();
		this.roleName=new RoleMasterDaoImpl().getRecordFromId(um.getRoleId()).getRoleName();
		System.out.println(this);
		if(um.getStateId()!=null && this.roleName.equalsIgnoreCase("SRLM ADMIN")){
			this.stateName=um.getStateId().getStateName();
			this.entityCode=um.getStateId().getStateId();
		}
		
	}
	@Override
	public String toString() {
		return "LoginVO [userid=" + userid + ", userName=" + userName + ", userDesignation=" + userDesignation
				+ ", entityCode=" + entityCode + ", stateName=" + stateName + ", roleId=" + roleId + ", roleName="
				+ roleName + "]";
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getVillagePanchayat() {
		return villagePanchayat;
	}
	public void setVillagePanchayat(String villagePanchayat) {
		this.villagePanchayat = villagePanchayat;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreationOn() {
		return creationOn;
	}
	public void setCreationOn(String creationOn) {
		this.creationOn = creationOn;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
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
	public String getUserDesignation() {
		return userDesignation;
	}
	public void setUserDesignation(String userDesignation) {
		this.userDesignation = userDesignation;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getPanchayatRole() {
		return panchayatRole;
	}
	public void setPanchayatRole(String panchayatRole) {
		this.panchayatRole = panchayatRole;
	}
	public String getBlockPanchayat() {
		return blockPanchayat;
	}
	public void setBlockPanchayat(String blockPanchayat) {
		this.blockPanchayat = blockPanchayat;
	}
	public String getDistrictPanchayat() {
		return districtPanchayat;
	}
	public void setDistrictPanchayat(String districtPanchayat) {
		this.districtPanchayat = districtPanchayat;
	}
	public String getBlockPanch() {
		return blockPanch;
	}
	public void setBlockPanch(String blockPanch) {
		this.blockPanch = blockPanch;
	}
	public String getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	public String getEntityCodeLevel() {
		return entityCodeLevel;
	}
	public void setEntityCodeLevel(String entityCodeLevel) {
		this.entityCodeLevel = entityCodeLevel;
	}
	public String getSelPage() {
		return selPage;
	}
	public void setSelPage(String selPage) {
		this.selPage = selPage;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getOldEntityCode() {
		return OldEntityCode;
	}
	public void setOldEntityCode(String oldEntityCode) {
		OldEntityCode = oldEntityCode;
	}
	public String getOldEntityName() {
		return OldEntityName;
	}
	public void setOldEntityName(String oldEntityName) {
		OldEntityName = oldEntityName;
	}
	public String getOldEntityType() {
		return OldEntityType;
	}
	public void setOldEntityType(String oldEntityType) {
		OldEntityType = oldEntityType;
	}
	public String getLogged_in() {
		return logged_in;
	}
	public void setLogged_in(String logged_in) {
		this.logged_in = logged_in;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getLoginAttempt() {
		return loginAttempt;
	}
	public void setLoginAttempt(int loginAttempt) {
		this.loginAttempt = loginAttempt;
	}
	public void setLocationMasterCompleted(boolean locationMasterCompleted) {
		this.locationMasterCompleted = locationMasterCompleted;
	}
	public boolean isLocationMasterCompleted() {
		return locationMasterCompleted;
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
	 
	

}
