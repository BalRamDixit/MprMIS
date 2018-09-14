package com.infotech.sgsy.common;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @author 51512
 *
 */
public class MasterNRLMVO implements Serializable {
	
	private String stateUser;
	private String districtUser;
	private String blockUser;
	private String mordUser;
	private String genUser;
	private String stateCode;
	private String districtCode;
	private String blockCode;
	private String panchayatCode;
	private String createdOn;
	private String createdBy;
	private String lastModifiedOn;
	private String lastModifedBy;
	private String updatedOn;
	private String updatedBy;
	private String propertyCode;
	private String propertyValue;
	private String stateShortName;
	  

	private String followupMemberPhoto;
	private int successCount; // Added By CVAS2273 for display messages
	
	
	
	
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public String getStateShortName() {
		return stateShortName;
	}
	public void setStateShortName(String stateShortName) {
		this.stateShortName = stateShortName;
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
	public String getPanchayatCode() {
		return panchayatCode;
	}
	public void setPanchayatCode(String panchayatCode) {
		this.panchayatCode = panchayatCode;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastModifedBy() {
		return lastModifedBy;
	}
	public void setLastModifedBy(String lastModifedBy) {
		this.lastModifedBy = lastModifedBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getPropertyCode() {
		return propertyCode;
	}
	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getFollowupMemberPhoto() {
		return followupMemberPhoto;
	}
	public void setFollowupMemberPhoto(String followupMemberPhoto) {
		this.followupMemberPhoto = followupMemberPhoto;
	}
	public String getStateUser() {
		return stateUser;
	}
	public void setStateUser(String stateUser) {
		this.stateUser = stateUser;
	}
	public String getDistrictUser() {
		return districtUser;
	}
	public void setDistrictUser(String districtUser) {
		this.districtUser = districtUser;
	}
	public String getBlockUser() {
		return blockUser;
	}
	public void setBlockUser(String blockUser) {
		this.blockUser = blockUser;
	}
	public String getMordUser() {
		return mordUser;
	}
	public void setMordUser(String mordUser) {
		this.mordUser = mordUser;
	}
	public String getGenUser() {
		return genUser;
	}
	public void setGenUser(String genUser) {
		this.genUser = genUser;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(String lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
		
}