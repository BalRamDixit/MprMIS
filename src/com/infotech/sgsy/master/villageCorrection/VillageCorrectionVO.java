package com.infotech.sgsy.master.villageCorrection;

import java.util.Date;

public class VillageCorrectionVO {
	private String villageCode;
	private String villageName;
	private String stateCode;
	private String districtCode;
	private String blockCode;
	private String gramPanchayatCode;
	private Date createdOn;
	private String createdBy;
	private Date lastModifiedOn;
	private String lastModifedBy;
	private String ruralUrbanArea;
	private String flag;

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

	public String getGramPanchayatCode() {
		return gramPanchayatCode;
	}

	public void setGramPanchayatCode(String gramPanchayatCode) {
		this.gramPanchayatCode = gramPanchayatCode;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

	public String getLastModifedBy() {
		return lastModifedBy;
	}

	public void setLastModifedBy(String lastModifedBy) {
		this.lastModifedBy = lastModifedBy;
	}

	public String getRuralUrbanArea() {
		return ruralUrbanArea;
	}

	public void setRuralUrbanArea(String ruralUrbanArea) {
		this.ruralUrbanArea = ruralUrbanArea;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
