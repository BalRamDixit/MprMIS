 package com.infotech.sgsy.master.village;

import com.infotech.sgsy.common.MasterVO;

public class VillageVO extends MasterVO{
	
	private String gramPanchayatCode;
	private String villageCode;
	private String villageName;
	private String	ruralUrbanArea;
	private String blockName;
	private String gramPanchayatName;
	
	
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getGramPanchayatName() {
		return gramPanchayatName;
	}
	public void setGramPanchayatName(String gramPanchayatName) {
		this.gramPanchayatName = gramPanchayatName;
	}
	public String getGramPanchayatCode() {
		return gramPanchayatCode;
	}
	public void setGramPanchayatCode(String gramPanchayatCode) {
		this.gramPanchayatCode = gramPanchayatCode;
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
	public String getRuralUrbanArea() {
		return ruralUrbanArea;
	}
	public void setRuralUrbanArea(String ruralUrbanArea) {
		this.ruralUrbanArea = ruralUrbanArea;
	}
}
