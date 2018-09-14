package com.infotech.sgsy.master.grampanchayatCorrection;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.infotech.sgsy.master.villageCorrection.VillageCorrectionVO;

public class GrampanchayatCorrectionVO  {
	private String gramPanchayatCode;
	private String ruralUrbanArea;
	private String gramPanchayatName;
	private Date createdOn;
	private String createdBy;
	private Date lastModifiedOn;
	private String lastModifedBy;
	private String stateCode;
	private String districtCode;
	private String blockCode;
	private String flag;
	private Set<VillageCorrectionVO> villageCorrectionSet = new HashSet<VillageCorrectionVO>();
	public String getGramPanchayatCode() {
		return gramPanchayatCode;
	}
	public void setGramPanchayatCode(String gramPanchayatCode) {
		this.gramPanchayatCode = gramPanchayatCode;
	}
	public String getRuralUrbanArea() {
		return ruralUrbanArea;
	}
	public void setRuralUrbanArea(String ruralUrbanArea) {
		this.ruralUrbanArea = ruralUrbanArea;
	}
	public String getGramPanchayatName() {
		return gramPanchayatName;
	}
	public void setGramPanchayatName(String gramPanchayatName) {
		this.gramPanchayatName = gramPanchayatName;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Set<VillageCorrectionVO> getVillageCorrectionSet() {
		return villageCorrectionSet;
	}
	public void setVillageCorrectionSet(
			Set<VillageCorrectionVO> villageCorrectionSet) {
		this.villageCorrectionSet = villageCorrectionSet;
	}
	
}
