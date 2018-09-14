package com.infotech.sgsy.login;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;
/**
 * 
 * @author 37595
 *  Date : 28.04.2009
 */

public class LoginActionForm extends ActionForm{
	
	private String userid;
	private String password;
	private String userName;
	private String userDesignation;
	private String entityCode;
	private String levelCode;
	private String statusFlag;
	private String entityName;
	private String entityType;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	private String panchayatRole;
	private String statePanchayat;
	private String districtPanchayat;
	private String blockPanchayat;
	private String villagePanchayat;
	private String blockPanch;
	
	private String language;
	private String selPage="selPage";
	
	
	private String OldEntityType;
	private String ipAddress;
	
	public String getOldEntityType() {
		return OldEntityType;
	}
	public void setOldEntityType(String oldEntityType) {
		OldEntityType = oldEntityType;
	}
	public String getSelPage() {
		return selPage;
	}
	public void setSelPage(String selPage) {
		this.selPage = selPage;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getVillagePanchayat() {
		return villagePanchayat;
	}
	public void setVillagePanchayat(String villagePanchayat) {
		this.villagePanchayat = villagePanchayat;
	}
	public String getBlockPanchayat() {
		return blockPanchayat;
	}
	public void setBlockPanchayat(String blockPanchayat) {
		this.blockPanchayat = blockPanchayat;
	}
	public String getPanchayatRole() {
		return panchayatRole;
	}
	public void setPanchayatRole(String panchayatRole) {
		this.panchayatRole = panchayatRole;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUserDesignation() {
		return userDesignation;
	}
	public void setUserDesignation(String userDesignation) {
		this.userDesignation = userDesignation;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
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
	public String getStatePanchayat() {
		return statePanchayat;
	}
	public void setStatePanchayat(String statePanchayat) {
		this.statePanchayat = statePanchayat;
	}
	
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public void reset() {
		
		userid="";
		password="";
		userName="";
		userDesignation="";
		entityCode="";
		levelCode="";
		statusFlag="";
		entityName="";
		entityType="";
		oldPassword="";
		newPassword="";
		confirmPassword="";
		
		panchayatRole="";
		statePanchayat="";
		districtPanchayat="";
		blockPanchayat="";
		villagePanchayat="";
		blockPanch="";
	}
	
	public void resetSelectPage() {
		
		panchayatRole="";
		statePanchayat="";
		districtPanchayat="";
		blockPanchayat="";
		villagePanchayat="";
		blockPanch="";
	}
	

}
