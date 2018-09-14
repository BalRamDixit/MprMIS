package com.infotech.sgsy.common;

import java.io.Serializable;

public class LocationVO implements Serializable{
	
	private String stateCode;
	private String stateShortName;
	private String stateName;
	private String districtCode;
	private String districtName;
	private String area;
	private String subDistrictMunicipalCode;
	private String subDistrictMunicipalName;
	private String blockCode;
	private String blockName;
	private String villageCode;
	private String villageName;
	private String drdaCode;
	private String drdaName;
	private String finYr;
	private String month;
	private String wingCode;
	private String wingName;
	
	
	public String getWingCode() {
		return wingCode;
	}
	public void setWingCode(String wingCode) {
		this.wingCode = wingCode;
	}
	public String getWingName() {
		return wingName;
	}
	public void setWingName(String wingName) {
		this.wingName = wingName;
	}
	public String getFinYr() {
		return finYr;
	}
	public void setFinYr(String finYr) {
		this.finYr = finYr;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSubDistrictMunicipalCode() {
		return subDistrictMunicipalCode;
	}
	public void setSubDistrictMunicipalCode(String subDistrictMunicipalCode) {
		this.subDistrictMunicipalCode = subDistrictMunicipalCode;
	}
	public String getSubDistrictMunicipalName() {
		return subDistrictMunicipalName;
	}
	public void setSubDistrictMunicipalName(String subDistrictMunicipalName) {
		this.subDistrictMunicipalName = subDistrictMunicipalName;
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

	
	
}
