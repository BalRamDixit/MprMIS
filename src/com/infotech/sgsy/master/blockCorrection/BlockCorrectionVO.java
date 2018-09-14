package com.infotech.sgsy.master.blockCorrection;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionVO;
import com.infotech.sgsy.master.villageCorrection.VillageCorrectionVO;

public class BlockCorrectionVO {
	private String blockCode;
	private String ruralUrbanArea;
	private String blockName;
	private Date createdOn;
	private String createdBy;
	private Date lastModifiedOn;
	private String lastModifedBy;
	private String stateCode;
	private String districtCode;
	private String flag;
	private Set<GrampanchayatCorrectionVO> grampanchayatCorrectionSet = new HashSet<GrampanchayatCorrectionVO>();
	private Set<VillageCorrectionVO> villageCorrectionSet = new HashSet<VillageCorrectionVO>();

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getRuralUrbanArea() {
		return ruralUrbanArea;
	}

	public void setRuralUrbanArea(String ruralUrbanArea) {
		this.ruralUrbanArea = ruralUrbanArea;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Set<GrampanchayatCorrectionVO> getGrampanchayatCorrectionSet() {
		return grampanchayatCorrectionSet;
	}

	public void setGrampanchayatCorrectionSet(Set<GrampanchayatCorrectionVO> grampanchayatCorrectionSet) {
		this.grampanchayatCorrectionSet = grampanchayatCorrectionSet;
	}

	public Set<VillageCorrectionVO> getVillageCorrectionSet() {
		return villageCorrectionSet;
	}

	public void setVillageCorrectionSet(
			Set<VillageCorrectionVO> villageCorrectionSet) {
		this.villageCorrectionSet = villageCorrectionSet;
	}
}
