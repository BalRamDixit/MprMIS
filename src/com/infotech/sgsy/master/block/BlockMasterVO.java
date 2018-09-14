package com.infotech.sgsy.master.block;

import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.master.grampanchayat.GramPanchayatVO;
import com.infotech.sgsy.master.village.VillageVO;

import java.util.HashSet;
import java.util.Set;
public class BlockMasterVO extends MasterVO{

	public BlockMasterVO() {
	      super();
	   }

	private String blockName;
	private String blockOfficeName;
	private String blockOfficeAdd;
	private String blockShortName;
	private String ruralUrbanArea;
	private String activeFlag;
	
	private String stateCode;
	private String districtCode;
	private Set<GramPanchayatVO> grampanchayatSet = new HashSet<GramPanchayatVO>();
	private Set<VillageVO> villageSet = new HashSet<VillageVO>();
	private String intensive;
	
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getBlockOfficeName() {
		return blockOfficeName;
	}
	public void setBlockOfficeName(String blockOfficeName) {
		this.blockOfficeName = blockOfficeName;
	}
	public String getBlockOfficeAdd() {
		return blockOfficeAdd;
	}
	public void setBlockOfficeAdd(String blockOfficeAdd) {
		this.blockOfficeAdd = blockOfficeAdd;
	}
	public String getBlockShortName() {
		return blockShortName;
	}
	public void setBlockShortName(String blockShortName) {
		this.blockShortName = blockShortName;
	}
	public String getRuralUrbanArea() {
		return ruralUrbanArea;
	}
	public void setRuralUrbanArea(String ruralUrbanArea) {
		this.ruralUrbanArea = ruralUrbanArea;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
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
	public Set<GramPanchayatVO> getGrampanchayatSet() {
		return grampanchayatSet;
	}
	public void setGrampanchayatSet(Set<GramPanchayatVO> grampanchayatSet) {
		this.grampanchayatSet = grampanchayatSet;
	}
	public Set<VillageVO> getVillageSet() {
		return villageSet;
	}
	public void setVillageSet(Set<VillageVO> villageSet) {
		this.villageSet = villageSet;
	}
	public String getIntensive() {
		return intensive;
	}
	public void setIntensive(String intensive) {
		this.intensive = intensive;
	}
}
